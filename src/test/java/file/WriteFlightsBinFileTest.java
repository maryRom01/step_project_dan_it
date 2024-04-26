package file;

import DAO.DAOinterfaceImpl.CollectionFlightDAO;
import DAO.DAOinterface.FlightDAO;
import org.junit.Test;
import service.serviseInterfaceImpl.FlightServiceImpl;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class WriteFlightsBinFileTest {

    @Test
    public void generateFlightsBinFileTest() {
        FlightDAO flightDAO = new CollectionFlightDAO();
        FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
        flightDAO.generateRandomFlights();
        flightServiceImpl.sendData("flights.bin");
        flightServiceImpl.loadData();
        assertTrue("The data loaded with error", flightServiceImpl.getAllFlightsForSpecificDay().size() == CollectionFlightDAO.randomFlightsAmount);
        flightServiceImpl.getAllFlightsForSpecificDay().forEach(flight -> System.out.println(flight));
    }

    @Test
    public void deleteFlightsBinFileTest() {
        FlightDAO flightDAO = new CollectionFlightDAO();
        FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
        flightDAO.generateRandomFlights();
        flightServiceImpl.sendData("flights.bin");
        flightServiceImpl.loadData();
        assertTrue("The data loaded with error", flightServiceImpl.getAllFlightsForSpecificDay().size() == CollectionFlightDAO.randomFlightsAmount);
        File generatedFile = new File("flights.bin");
        generatedFile.delete();
    }
}
