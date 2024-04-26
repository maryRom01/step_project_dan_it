package service.serviseInterfaceImpl;
import DAO.DAOinterface.BookingDAO;

import dto.BookingFlightDTO;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import service.serviseInterface.BookingService;
import utils.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author Alexander Isai on 16.04.2024.
 */
public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    int createBookingId(){
        return getAllBookings().stream()
                .filter(Objects::nonNull)
                .mapToInt(Booking::getId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void createNewBooking(BookingFlightDTO bookingFlightDTO) {
        Passenger passenger = new Passenger(bookingFlightDTO.getName(), bookingFlightDTO.getSurname());
        int index = getAllPassengers().indexOf(passenger);
        if(index >= 0){
            passenger = getAllPassengers().get(index);
        }
        Booking booking = new Booking(passenger, bookingFlightDTO.getFlight(), createBookingId());
        passenger.addBooking(booking);
        bookingDAO.saveBooking(booking);
    }

    @Override
    public List<Booking> getAllBookingByPassenger(Passenger passenger) {
        Logger.info("Пошук пасажира " + passenger + " в БД");
        return bookingDAO.getAllBooking().stream()
                .filter(booking -> booking != null && booking.getPassenger().equals(passenger))
                .toList();
    }

    @Override
    public <T> void displayItems(List<T> items) {
        AtomicInteger i = new AtomicInteger(1);
        items.forEach(item -> System.out.println(i.getAndIncrement() + ". " + item));
    }

    @Override
    public void cancelBooking(int id) {
        bookingDAO.cancelBooking(getBookingById(id));
    }
    @Override
    public List<Passenger> getAllPassengers() {
        Logger.info("Завантажений список пасажирів");
        return new ArrayList<>(new HashSet<>(bookingDAO.getAllBooking().stream()
                .filter(Objects::nonNull)
                .map(Booking::getPassenger)
                .toList()));
    }

    @Override
    public Passenger getActivePassenger(String name, String surname, String password) {
        Passenger activePassenger = new Passenger(name, surname);
        activePassenger.setPassword(password);
        Optional<Passenger> exceptPassenger = getAllPassengers().stream()
                        .filter(passenger -> passenger != null && passenger.equals(activePassenger))
                                .findFirst();
        if (exceptPassenger.isPresent()){
            Logger.info("Знайдено пасажира " + exceptPassenger.get() + " в БД");
            return exceptPassenger.get();
        }
        else {
            Logger.info("Створено та додано нового пасажира  " + activePassenger + " в БД");
            System.out.println(name + ", Ви новий пасажир. Вітаємо!");
            return activePassenger;
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBooking();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingDAO.getBookingById(id);
    }

    @Override
    public Flight getFlightByBookingId(int id) {
        return getBookingById(id).getFlight();
    }

    @Override
    public List<Integer> getBookingsIdsByPassenger(Passenger passenger) {
        return getAllBookingByPassenger(passenger).stream()
                .filter(Objects::nonNull)
                .map(Booking::getId)
                .toList();
    }
}
