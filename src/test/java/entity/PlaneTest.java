package entity;

import entity.Plane;
import ErrorException.PlaneException;
import entity.enums.PlaneModel;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlaneTest {

    @Test
    public void testDefaultSeats() {
        Plane plane = new Plane();
        int expectedSeatsByDefault = plane.getDefaultSeatsAmount();
        assertAll("Should test default Seats values",
                () -> assertEquals("Amount of seats is not equal to default", expectedSeatsByDefault, plane.getSeats()),
                () -> assertEquals("Amount of free seats is not equal to default", plane.getAvailableSeats(), plane.getDefaultSeatsAmount()),
                () -> assertEquals("Amount of booked seats is not equal to 0", plane.getBookedSeats(), 0)
        );
    }

    @Test
    public void testPositiveParameterizedSeats() {
        int amountOfSeats = 285;
        Plane plane = new Plane(PlaneModel.Boeing_787_9);
        assertAll("Should test parameterized Seats values",
                () -> assertEquals("Amount of seats is not equal to parameter", amountOfSeats, plane.getSeats()),
                () -> assertEquals("Amount of free seats is not equal to default", amountOfSeats, plane.getAvailableSeats() ),
                () -> assertEquals("Amount of booked seats is not equal to 0", plane.getBookedSeats(), 0)
        );
    }

    @Test
    public void testNegativeSetBookedSeats() {
        PlaneException thrown = assertThrows(PlaneException.class, () -> {
            Plane plane = new Plane(PlaneModel.CRJ_200);
            plane.setBookedSeats(51);
        });
        assertTrue("SeatsException does not contain expected text", Plane.errorNotEnoughFreeSeats.contains(thrown.getMessage()));
    }

    @Test
    public void testPositiveSetBookedSeats() {
        int amountOfSeats = 128;
        int bookedSeats = 5;
        Plane plane = new Plane(PlaneModel.A_319);
        plane.setBookedSeats(bookedSeats);
        assertAll("Should test setting Seats values",
                () -> assertEquals("Amount of seats is not equal to parameter", amountOfSeats, plane.getSeats()),
                () -> assertEquals("Amount of free seats is not equal to default", amountOfSeats - bookedSeats, plane.getAvailableSeats() ),
                () -> assertEquals("Amount of booked seats is not equal to 0", plane.getBookedSeats(), bookedSeats)
        );
    }

    @Test
    public void testPositiveSetBookedSeats2() {
        int amountOfSeats = 273;
        int bookedSeats = 5;
        Plane plane = new Plane(PlaneModel.Boeing_777_200);
        plane.setBookedSeats(bookedSeats);
        assertAll("Should test setting Seats values",
                () -> assertEquals("Amount of seats is not equal to parameter", amountOfSeats, plane.getSeats()),
                () -> assertEquals("Amount of free seats is not equal to default", amountOfSeats - bookedSeats, plane.getAvailableSeats() ),
                () -> assertEquals("Amount of booked seats is not equal to 0", plane.getBookedSeats(), bookedSeats)
        );
    }
}
