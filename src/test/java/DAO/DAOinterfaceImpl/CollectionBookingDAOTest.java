package DAO.DAOinterfaceImpl;

import entity.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.fileLoader.FileBookingLoaderBin;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CollectionBookingDAOTest {

    @Mock
    private FileBookingLoaderBin bookingLoaderBin;

    @InjectMocks
    private CollectionBookingDAO bookingDAO;

    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    public void setup() {
        booking1 = new Booking();
        booking1.setId(1);
        booking2 = new Booking();
        booking2.setId(2);

        lenient().when(bookingLoaderBin.getDataFromFile()).thenReturn(Arrays.asList(booking1, booking2));
    }

    @Test
    public void testGetBookingById() {
        assertNotNull(bookingDAO.getAllBooking());
        assertFalse(bookingDAO.getAllBooking().isEmpty());

        bookingDAO.setBookings(bookingDAO.getAllBooking());
        Booking result = bookingDAO.getBookingById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testSaveBooking() {
        Booking booking3 = new Booking();
        booking3.setId(3);

        bookingDAO.saveBooking(booking3);

        verify(bookingLoaderBin).loadData(any());
    }

    @Test
    public void testCancelBooking() {
        bookingDAO.cancelBooking(booking1);

        verify(bookingLoaderBin).loadData(any());
    }

    @Test
    public void testGetAllBooking() {
        List<Booking> bookings = bookingDAO.getAllBooking();
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }
}