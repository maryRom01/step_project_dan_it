package controller;

import dto.SearchFlightDTO2;
import entity.Flight;
import service.serviseInterfaceImpl.FlightServiceImpl;

import java.io.IOException;
import java.util.List;

public class FlightController {

    private final FlightServiceImpl flightServiceImpl;

    public FlightController(FlightServiceImpl flightServiceImpl) {
        this.flightServiceImpl = flightServiceImpl;
    }

    public List<Flight> getAllFlights() {
        return flightServiceImpl.getAllFlightsForSpecificDay();
    };

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightServiceImpl.getFlightByFlightNumber(flightNumber);
    };

    public boolean deleteFlight(String flightNumber) {
        return flightServiceImpl.deleteFlight(flightNumber);
    };

    public boolean deleteFlight(Flight flight) {
        return flightServiceImpl.deleteFlight(flight);
    };

    public void saveFlight(Flight flight) {
        flightServiceImpl.saveFlight(flight);
    };

    public int getAllSeats(Flight flight) {
        return flightServiceImpl.getAllSeats(flight);
    };

    public int getAvailableSeats(Flight flight) {
        return flightServiceImpl.getAvailableSeats(flight);
    };

    public int getBookedSeats(Flight flight) {
        return flightServiceImpl.getBookedSeats(flight);
    };

    public void addFlight(Flight flight) {
        flightServiceImpl.addFlight(flight);
    };

    public void displayAllFlights() {
        flightServiceImpl.displayAllFlights();
    };

    public void displayAllFlights(List<Flight> flights) {
        flightServiceImpl.displayAllFlights(flights);
    }

    public List<Flight> searchFlight(SearchFlightDTO2 searchFlightDTO2) {
        return flightServiceImpl.searchFlight(searchFlightDTO2);
    }

    public String getSpecificFlightDetails(Flight flight) {
        return flightServiceImpl.getSpecificFlightDetails(flight);
    }

    public void bookSeats(Flight flight, int bookedSeats) throws IOException {
        flightServiceImpl.bookSeats(flight, bookedSeats);
    }
}
