package controller;

import DAO.DAOinterface.FlightDAO;
import DAO.DAOinterfaceImpl.CollectionFlightDAO;
import entity.Flight;
import org.junit.Test;
import service.serviseInterfaceImpl.FlightServiceImpl;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FlightControllerTest {

    FlightDAO flightDAO = new CollectionFlightDAO();
    FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
    FlightController flightController = new FlightController(flightServiceImpl);

    @Test
    public void getAllFlightsTest() {
        flightServiceImpl.loadData();
        List<Flight> flights = flightController.getAllFlights();
        flights.forEach(flight -> {
            assertTrue("The date should be current day or the next day",
                    flight.getDate().getDate() == new Date().getDate()
                                || flight.getDate().getDate() == new Date().getDate() + 1);
        });
    }

    @Test
    public void searchFlightTest() {
        flightServiceImpl.loadData();
        List<Flight> flights = flightController.getAllFlights();
        Date currentDate = new Date();
        flights.forEach(flight -> {
            assertTrue("The date should be current day or the next day",
                    (flight.getDate().getDate() == currentDate.getDate()
                                || flight.getDate().getDate() == currentDate.getDate() + 1
                            )
                            && (
                                    flight.getDate().getYear() == currentDate.getYear() + 1900
                            )
                            && (
                                    flight.getDate().getMonth() == currentDate.getMonth() + 1
                            )
            );
        });
    }
}
