package service.serviseInterface;

import dto.SearchFlightDTO2;
import entity.Flight;

import java.io.IOException;
import java.util.List;

public interface FlightService {

    List<Flight> getAllFlightsForSpecificDay();
    Flight getFlightByFlightNumber(String flightNumber);
    boolean deleteFlight(String flightNumber);
    boolean deleteFlight(Flight flight);
    void saveFlight(Flight flight);
    int getAllSeats(Flight flight);
    int getAvailableSeats(Flight flight);
    int getBookedSeats(Flight flight);
    void addFlight(Flight flight);
    void displayAllFlights();
    void displayAllFlights(List<Flight> flights);
    void loadData();
    void sendData(String filePath);
    void saveData() throws IOException;
    List<Flight> searchFlight(SearchFlightDTO2 searchFlightDTO);
    String getSpecificFlightDetails(Flight flight);
    void bookSeats(Flight flight, int seats) throws IOException;
}
