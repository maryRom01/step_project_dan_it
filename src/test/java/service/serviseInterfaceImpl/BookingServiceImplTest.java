package service.serviseInterfaceImpl;

import DAO.DAOinterface.BookingDAO;
import dto.BookingFlightDTO;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingDAO bookingDAO;

    @Mock
    private Flight flight;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingFlightDTO bookingFlightDTO;
    private Booking booking;
    private Passenger passenger;

    @BeforeEach
    public void setup() {
        passenger = new Passenger("Ivan", "Ivanov");
        bookingFlightDTO = new BookingFlightDTO("Ivan", "Ivanov", flight);
        booking = new Booking(passenger, flight, 1);
    }

    @Test
    public void testCreateNewBooking() {
        when(bookingDAO.getAllBooking()).thenReturn(new ArrayList<>());
        bookingService.createNewBooking(bookingFlightDTO);
        verify(bookingDAO).saveBooking(any());
    }

    @Test
    public void testGetAllBookingByPassenger() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingDAO.getAllBooking()).thenReturn(bookings);

        List<Booking> results = bookingService.getAllBookingByPassenger(passenger);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(bookingDAO).getAllBooking();
    }

    @Test
    public void testCancelBooking() {
        when(bookingDAO.getBookingById(1)).thenReturn(booking);
        bookingService.cancelBooking(1);
        verify(bookingDAO).cancelBooking(booking);
    }

    @Test
    public void testGetAllPassengers() {
        List<Booking> bookings = List.of(booking);
        when(bookingDAO.getAllBooking()).thenReturn(bookings);

        List<Passenger> passengers = bookingService.getAllPassengers();
        assertFalse(passengers.isEmpty());
        assertEquals(1, passengers.size());
        assertTrue(passengers.contains(passenger));
    }

    @Test
    public void testGetAllBookings() {
        when(bookingDAO.getAllBooking()).thenReturn(new ArrayList<>());
        List<Booking> bookings = bookingService.getAllBookings();
        assertNotNull(bookings);
        verify(bookingDAO).getAllBooking();
    }


}