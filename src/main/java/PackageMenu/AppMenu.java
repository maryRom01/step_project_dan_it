package PackageMenu;

import DAO.DAOinterface.BookingDAO;
import DAO.DAOinterface.FlightDAO;
import DAO.DAOinterfaceImpl.CollectionBookingDAO;
import DAO.DAOinterfaceImpl.CollectionFlightDAO;
import ErrorException.InvalidMenuItemException;
import controller.BookingController;
import controller.FlightController;
import service.serviseInterfaceImpl.BookingServiceImpl;
import service.serviseInterfaceImpl.FlightServiceImpl;

import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        FlightDAO flightDAO = new CollectionFlightDAO();
        FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
        FlightController flightController = new FlightController(flightServiceImpl);
        flightServiceImpl.loadData();
        BookingDAO bookingDAO = new CollectionBookingDAO();
        BookingServiceImpl bookingService = new BookingServiceImpl(bookingDAO);
        BookingController bookingController = new BookingController(bookingService);

        ConsoleClass consoleClass = new ConsoleClass(flightController, bookingController);
        Scanner scan = new Scanner(System.in);
        boolean isLogin = false;
        boolean isTrue = true;

        while (isTrue) {
            if (!isLogin) {
                isLogin = consoleClass.authenticate();
                if (!isLogin) {
                    System.out.println("Невірний логін або пароль. Спробуйте ще раз.");
                    continue;
                }
            }

            consoleClass.getNewMenu().forEach(System.out::println);
            System.out.println("Зробіть ваш вибір: ");
            String paragraph = scan.next().trim().toLowerCase();

            try{
                switch (paragraph) {
                    case "1":
                        consoleClass.onlineScoreboard();
                        break;
                    case "2":
                        consoleClass.setFlightNumber();
                        break;
                    case "3":
                        consoleClass.flightDetails();
                        break;
                    case "4":
                        consoleClass.setReservationId();
                        break;
                    case "5":
                        consoleClass.fullName();
                        break;
                    case "6":
                        isLogin = false;
                        System.out.println("Вихід з облікового запису.");
                        break;
                    case "7":
                        isTrue = false;
                        System.out.println("До побачення, будемо ради вас бачити знову!");
                        break;
                    default:
                        throw new InvalidMenuItemException("Будь-ласка введіть коректне значення!");
                }
            } catch (InvalidMenuItemException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
