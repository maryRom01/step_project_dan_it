
package DAO.DAOinterface;

import entity.Flight;

import java.util.List;
import java.util.Set;

public interface FlightDAO {

    Set<entity.Flight> getAllFlights();

    Flight getFlightByFlightNumber(String flightNumber);

    String getSpecificFlightDetails(Flight flight);

    boolean deleteFlight(String flightNumber);

    boolean deleteFlight(Flight flight);

    void saveFlight(Flight flight);

    int getAllSeats(Flight flight);

    void setFlights(Set<Flight> flights);

    int getAvailableSeats(Flight flight);

    int getBookedSeats(Flight flight);

    void addFlight(Flight flight);

    void displayAllFlights(List<Flight> flights);

    Set<Flight> generateRandomFlights();

    void loadData(Set<Flight> flights);

}
