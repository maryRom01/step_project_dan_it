package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * description
 *
 * @author Alexander Isai on 14.04.2024.
 */

public class Booking implements Serializable {

    private int id;
    private Passenger passenger;
    private Flight flight;

    public Booking() {
    }

    public Booking(Passenger passenger, Flight flight, int id) {
        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(passenger, booking.passenger) && Objects.equals(flight, booking.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, flight);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }
}