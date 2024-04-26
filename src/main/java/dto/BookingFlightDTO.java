package dto;

import entity.Flight;

import java.util.Objects;

/**
 * This is a DTO file for transferring information
 * from the user (name, surname, number of passengers)
 * to the matching flight search service.
 * @author Alexander Isai on 14.04.2024.
 */
public class BookingFlightDTO {

    private String name;
    private String surname;
    private Flight flight;

    public BookingFlightDTO(String name, String surname, Flight flight) {
        this.name = name;
        this.surname = surname;
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingFlightDTO that = (BookingFlightDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && flight.getFlightNumber().equals(that.flight.getFlightNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, flight.getFlightNumber());
    }

    @Override
    public String toString() {
        return "BookingFlightDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number of flight=" + flight.getFlightNumber() +
                '}';
    }
}
