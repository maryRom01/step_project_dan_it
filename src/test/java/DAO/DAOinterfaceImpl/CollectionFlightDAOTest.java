package DAO.DAOinterfaceImpl;

import DAO.DAOinterface.FlightDAO;
import entity.Flight;
import org.junit.Test;
import service.serviseInterfaceImpl.FlightServiceImpl;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CollectionFlightDAOTest {
    FlightDAO flightDAO = new CollectionFlightDAO();
    FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);

    @Test
    public void collectionFlightGenerateRandomFlightsTest() {
        Set<Flight> flights = flightDAO.generateRandomFlights();
        assertAll(() -> {
            assertTrue("The list of flights is empty", flights.size() > 0);
            assertTrue("The list of flights is more/less than expected", flights.size() == CollectionFlightDAO.randomFlightsAmount);
        });
    }

    @Test
    public void getAllFlightsTest() {
        flightServiceImpl.loadData();
        assertTrue(flightDAO.getAllFlights().size() == CollectionFlightDAO.randomFlightsAmount);
    }

    @Test
    public void getFlightByFlightNumberTest() {
        flightServiceImpl.loadData();
        Set<Flight> flights = flightDAO.getAllFlights();
        Optional<Flight> flight = flights.stream().findFirst();
        if (flight.isPresent()) {
            Flight retrieved = flight.get();
            assertNotNull(flightDAO.getFlightByFlightNumber(retrieved.getFlightNumber()));
        }
    }

    @Test
    public void getSpecificFlightDetailsTest() {
        flightServiceImpl.loadData();
        Set<Flight> flights = flightDAO.getAllFlights();
        Optional<Flight> flight = flights.stream().findFirst();
        if (flight.isPresent()) {
            Flight retrieved = flight.get();
            String specificFlightDetails = flightDAO.getSpecificFlightDetails(retrieved);
            String expectedDestination = retrieved.getDestination().name().charAt(0) + retrieved.getDestination().name().substring(1).toLowerCase();
            String expectedAviaCompany = retrieved.getAviaCompany().name();
            assertAll(() -> {
                assertTrue("The result should return flight number", specificFlightDetails.contains(retrieved.getFlightNumber()));
                assertTrue("The result should return destination", specificFlightDetails.contains(expectedDestination));
                assertTrue("The result should return avia companyr", specificFlightDetails.contains(expectedAviaCompany));
            });
        }
    }
}
