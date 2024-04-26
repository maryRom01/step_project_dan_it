package entity;

import entity.Flight;
import entity.Plane;
import entity.enums.AviaCompany;
import entity.enums.City;
import entity.enums.PlaneModel;
import org.junit.Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FlightTest {

    @Test
    public void positiveFlightTest() {
        Flight flight = new Flight(
                AviaCompany.DonbassAero,
                new Date(2024, Calendar.APRIL, 25),
                new Time(0, 15, 0),
                City.BERLIN,
                new Plane(PlaneModel.A_319));
        assertAll("Should test Flight values",
                () -> assertEquals("Amount of seats is not equal to expected", 128, flight.getSeats()),
                () -> assertEquals("Destination is not equal to expected", City.BERLIN, flight.getDestination()),
                () -> assertEquals("Duration is not equal to expected", new Time(0, 15, 0), flight.getDuration())
        );
    }

    @Test
    public void positiveFlightTest2() {
        Flight flight = new Flight(
                AviaCompany.AerosvitUkrainianAirlines,
                new Date(2024, Calendar.APRIL, 26),
                new Time(6, 35, 0),
                City.AMSTERDAM,
                new Plane(PlaneModel.Boeing_737));
        assertAll("Should test Flight values",
                () -> assertEquals("Amount of seats is not equal to expected", 127, flight.getSeats()),
                () -> assertEquals("Destination is not equal to expected", City.AMSTERDAM, flight.getDestination()),
                () -> assertEquals("Duration is not equal to expected", new Time(6, 35, 0), flight.getDuration()),
                () -> assertEquals("Duration is not equal to expected", AviaCompany.AerosvitUkrainianAirlines, flight.getAviaCompany())
        );
    }
}
