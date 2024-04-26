package entity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingTest {
    @Mock
    private Passenger mockPassenger1;
    @Mock
    private Passenger mockPassenger2;
    @Mock
    private Flight mockFlight1;
    @Mock
    private Flight mockFlight2;

    private Booking booking1;
    private Booking booking2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        booking1 = new Booking(mockPassenger1, mockFlight1, 1);
        booking2 = new Booking(mockPassenger1, mockFlight1, 2);
    }

    @Test
    public void testBookingObjectsAreNotEqual() {
        assertNotEquals("Expected bookings not to be equal", booking1, booking2);
    }

    @Test
    public void testBookingObjectsAreNotEqualWithDifferentPassenger() {
        Booking booking3 = new Booking(mockPassenger2, mockFlight1, 3);
        assertNotEquals("Expected bookings to not be equal due to different passengers", booking1, booking3);
    }

    @Test
    public void testBookingObjectsAreNotEqualWithDifferentFlight() {
        Booking booking3 = new Booking(mockPassenger1, mockFlight2, 4);
        assertNotEquals("Expected bookings to not be equal due to different flights", booking1, booking3);
    }

    @Test
    public void testHashCodeConsistency() {
        assertNotEquals("Expected same hash code for identical bookings", booking1.hashCode(), booking2.hashCode());
    }

    @Test
    public void testHashCodeDifferentForDifferentObjects() {
        Booking booking3 = new Booking(mockPassenger2, mockFlight1, 5);
        assertNotEquals("Expected different hash codes for different bookings", booking1.hashCode(), booking3.hashCode());
    }

    @Test
    public void testToStringUsesMockToString() {
        when(mockPassenger1.toString()).thenReturn("MockPassenger1");
        when(mockFlight1.toString()).thenReturn("MockFlight1");
        String expected = "Booking{id=" + booking1.getId() + ", passenger=MockPassenger1, flight=MockFlight1}";
        assertEquals("Expected toString to match", expected, booking1.toString());
    }
}