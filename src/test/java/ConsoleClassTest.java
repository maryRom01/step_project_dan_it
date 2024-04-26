import DAO.DAOinterface.BookingDAO;
import DAO.DAOinterface.FlightDAO;
import DAO.DAOinterfaceImpl.CollectionBookingDAO;
import DAO.DAOinterfaceImpl.CollectionFlightDAO;
import ErrorException.InvalidMenuItemException;
import PackageMenu.ConsoleClass;
import controller.BookingController;
import controller.FlightController;
import entity.Flight;
import entity.enums.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.serviseInterfaceImpl.BookingServiceImpl;
import service.serviseInterfaceImpl.FlightServiceImpl;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ConsoleClassTest {
    FlightDAO flightDAO = new CollectionFlightDAO();
    FlightServiceImpl flightServiceImpl = new FlightServiceImpl(flightDAO);
    FlightController flightController = new FlightController(flightServiceImpl);

    BookingDAO bookingDAO = new CollectionBookingDAO();
    BookingServiceImpl bookingService = new BookingServiceImpl(bookingDAO);
    BookingController bookingController = new BookingController(bookingService);

    private final InputStream originalSystemIn = System.in;
    private ConsoleClass console;

    @Before
    public void setUp() {
        String input = "3\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        FlightController flightController = mock(FlightController.class);
        BookingController bookingController = mock(BookingController.class);

        console = new ConsoleClass(flightController, bookingController);
    }
    @After
    public void tearDown() {
        System.setIn(originalSystemIn);
    }
    @Test
    public void testSetName() {
        InputStream originalIn = System.in;
        try {
            String input = "Vasya";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            ConsoleClass myClass = new ConsoleClass(flightController, bookingController);
            String result = myClass.setName();
            assertEquals("Vasya", result);
        } finally {
            System.setIn(originalIn);
        }
    }
    @Test
    public void testSetSurName() {
        InputStream originalIn = System.in;
        try {
            String input = "Vasya";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            ConsoleClass myClass = new ConsoleClass(flightController, bookingController);
            String result = myClass.setSurname();
            assertEquals("Vasya", result);
        } finally {
            System.setIn(originalIn);
        }
    }
    @Test
    public void testSetCountPassengers() {
        int count = console.setCountPassengers();
        assertEquals(3, count);
    }
}
