package service.serviseInterfaceImpl;

import DAO.DAOinterfaceImpl.CollectionFlightDAO;
import DAO.DAOinterface.FlightDAO;
import entity.Flight;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightServiceImplTest {
    FlightDAO flightDAO = new CollectionFlightDAO();
    FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);

    @Test
    public void loadDataTest() {
        flightDAO.generateRandomFlights();
        flightServiceImpl.sendData("flights.bin");
        flightServiceImpl.loadData();
        assertTrue("The data loaded with error", flightServiceImpl.getAllFlightsForSpecificDay().size() != 0);
//        flightServiceImpl.getAllFlightsForSpecificDay().forEach(flight -> {
//            System.out.println(flightServiceImpl.getSpecificFlightDetails(flight));
//        });
//        File generatedFile = new File("flights.bin");
//        generatedFile.delete();
    }

    @Test
    public void bookFlightTest() throws IOException {
        FlightDAO flightDAO = new CollectionFlightDAO();
        FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
        flightServiceImpl.loadData();
        flightServiceImpl.displayAllFlights();
        String flightNumber = "LARM30884";
        flightServiceImpl.bookSeats(flightServiceImpl.getFlightByFlightNumber(flightNumber), 3);
        assertTrue("Seats are not booked", flightServiceImpl.getFlightByFlightNumber(flightNumber).getBookedSeats() > 2);
        System.out.println(flightServiceImpl.getSpecificFlightDetails(flightServiceImpl.getFlightByFlightNumber(flightNumber)));
    }

    @Test
    public void searchFlightTest() {
        flightServiceImpl.loadData();
        List<Flight> flights = flightServiceImpl.getAllFlightsForSpecificDay();
        System.out.println(flights.size());
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

    @Test
    public void getFlightByFlightNumberTest() {
        flightServiceImpl.loadData();
        List<Flight> flights = flightServiceImpl.getAllFlightsForSpecificDay();
        String flightNumber = flights.get(0).getFlightNumber();
        assertEquals("Search by flight number does not return expected flight", flightNumber, flightServiceImpl.getFlightByFlightNumber(flightNumber).getFlightNumber());
    }
}
