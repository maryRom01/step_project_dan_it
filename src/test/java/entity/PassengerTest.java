package entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PassengerTest {

    @Test
    public void testAddBooking() {
        Passenger passenger = new Passenger("Ivan", "Ivanov");
        Booking mockBooking = mock(Booking.class);

        passenger.addBooking(mockBooking);
        assertTrue(passenger.getBookings().contains(mockBooking));
        assertEquals(1, passenger.getBookings().size());

        passenger.addBooking(mockBooking);
        assertEquals(1, passenger.getBookings().size());
    }

    @Test
    public void testEqualsAndHashCode() {
        Passenger passenger1 = new Passenger("Ivan", "Ivanov");
        Passenger passenger2 = new Passenger("Ivan", "Ivanov");
        Passenger passenger3 = new Passenger("Petr", "Petrov");

        assertEquals(passenger1, passenger2);
        assertNotEquals(passenger1, passenger3);

        assertEquals(passenger1.hashCode(), passenger2.hashCode());
        assertNotEquals(passenger1.hashCode(), passenger3.hashCode());
    }

    @Test
    public void testToString() {
        Passenger passenger = new Passenger("Ivan", "Ivanov");
        String expectedString = "Passenger{name='Ivan', surname='Ivanov'}";
        assertEquals(expectedString, passenger.toString());
    }
}