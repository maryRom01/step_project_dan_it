package service.serviseInterfaceImpl;

import DAO.DAOinterface.FlightDAO;
import dto.SearchFlightDTO2;
import entity.Flight;
import service.serviseInterface.FlightService;
import utils.Logger;
import utils.fileLoader.FileLoaderBin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {
    private FlightDAO flightsDAO;
    private final String filePath = "flights.bin";

    public FlightServiceImpl(FlightDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    @Override
    public List<Flight> getAllFlightsForSpecificDay() {
        Logger.info("Отримати рейси за поточний день");
        Date currentDate = new Date();
        int expectedYear = currentDate.getYear() + 1900;
        int expectedMonth = currentDate.getMonth() + 1;
        List<Flight> result = flightsDAO.getAllFlights()
                .stream()
                .filter(flight -> {
                    boolean b = flight.getDate().getYear() == expectedYear
                            && flight.getDate().getMonth() == expectedMonth
                            && ((flight.getDate().getDate() == currentDate.getDate()
                                    && flight.getDate().getHours() >= currentDate.getHours())
                                || (flight.getDate().getDate() == currentDate.getDate() + 1)
                                    && flight.getDate().getHours() < currentDate.getHours());
                    return b;
                }).collect(Collectors.toList());
        return result;
    };

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        Logger.info("Отримати рейс за номером");
        return flightsDAO.getFlightByFlightNumber(flightNumber);
    };

    @Override
    public boolean deleteFlight(String flightNumber) {
        return flightsDAO.deleteFlight(flightNumber);
    };

    @Override
    public boolean deleteFlight(Flight flight) {
        return flightsDAO.deleteFlight(flight);
    };

    @Override
    public void saveFlight(Flight flight) {
        Logger.info("Зберегти рейс");
        flightsDAO.saveFlight(flight);
    };

    @Override
    public int getAllSeats(Flight flight) {
        Logger.info("Отримати всі місця");
        return flightsDAO.getAllSeats(flight);
    };

    @Override
    public int getAvailableSeats(Flight flight) {
        Logger.info("Отримати вільні місця");
        return flightsDAO.getAvailableSeats(flight);
    };

    @Override
    public int getBookedSeats(Flight flight) {
        Logger.info("Отримати заброньовані місця");
        return flightsDAO.getBookedSeats(flight);
    };

    @Override
    public void addFlight(Flight flight) {
        flightsDAO.addFlight(flight);
    };

    @Override
    public void displayAllFlights() {
        Logger.info("Вивести на екран всі рейси");
        List<Flight> flights = this.getAllFlightsForSpecificDay();
        flights.forEach(System.out::println);
    };

    @Override
    public void displayAllFlights(List<Flight> flights) {
        Logger.info("Вивести на екран рейси");
        flightsDAO.displayAllFlights(flights);
    }

    @Override
    public void loadData() {
        Logger.info("Завантажити рейси");
        FileLoaderBin fileLoaderBin = new FileLoaderBin();
        Set<Flight> objects;
        try {
            objects = fileLoaderBin.readFile(filePath);
            flightsDAO.loadData(objects);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Logger.error("Невдала спроба завантаження файлу");
            throw new RuntimeException();
        }
    }

    @Override
    public void sendData(String filePath) {
        Logger.info("Записати рейси в файл");
        FileLoaderBin fileLoaderBin = new FileLoaderBin();
        try {
            Set<Flight> flights = flightsDAO.generateRandomFlights();
            fileLoaderBin.writeFile(filePath, flights);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            Logger.error("Невдала спроба запису файлу");
        }
    }

    @Override
    public void saveData() throws IOException {
        Set<Flight> existingFlights = flightsDAO.getAllFlights();
        FileLoaderBin fileLoaderBin = new FileLoaderBin();
        fileLoaderBin.writeFile("flights.bin", existingFlights);
        Logger.info("Рейси збережено");
    }

    @Override
    public List<Flight> searchFlight(SearchFlightDTO2 searchFlightDTO) {
        Logger.info("Пошук рейса");
        int expectedYear = searchFlightDTO.getDate().getYear() + 1900;
        int expectedMonth = searchFlightDTO.getDate().getMonth() + 1;
        List<Flight> result = flightsDAO.getAllFlights()
                .stream()
                .filter(flight -> {
                    String city = flight.getDestination().name();
                    boolean b = (flight.getDate().getMonth() == expectedMonth)
                            && (flight.getDate().getYear() == expectedYear)
                            && city.equalsIgnoreCase(searchFlightDTO.getDestination())
                            ;
                    return b; })
                .filter(flight -> {
                    boolean b = ((flight.getDate().getDate() == searchFlightDTO.getDate().getDate()
                                    && flight.getDate().getHours() >= searchFlightDTO.getDate().getHours())
                                || (flight.getDate().getDate() == searchFlightDTO.getDate().getDate() + 1)
                                    && flight.getDate().getHours() < searchFlightDTO.getDate().getHours())
                            ;
                    return b; })
                .filter(flight -> flight.getAvailableSeats() >= searchFlightDTO.getPassQuantity())
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public String getSpecificFlightDetails(Flight flight) {
        Logger.info("Отримати деталі рейса");
        return flightsDAO.getSpecificFlightDetails(flight);
    }

    @Override
    public void bookSeats(Flight flight, int seats) throws IOException {
        Flight flightToBeUpdated = flightsDAO.getFlightByFlightNumber(flight.getFlightNumber());
        flightToBeUpdated.updateSeats(seats);
        saveData();
        Logger.info("Заброньовані місця");
    }
}
