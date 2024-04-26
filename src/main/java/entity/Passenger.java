package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author Alexander Isai on 14.04.2024.
 */
public class Passenger implements Serializable {

    private String name;
    private String surname;
    private String password;
    private List<Booking> bookings;

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.password = name.toLowerCase();
        this.bookings = new ArrayList<>();
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addBooking(Booking booking){
        if (booking == null){
            System.out.println("Якась дурня");
        }
        int index = bookings.indexOf(booking);
        if (index >= 0) {
            bookings.set(index, booking);
        } else {
            bookings.add(booking);
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(name, passenger.name) && Objects.equals(surname, passenger.surname) && Objects.equals(password, passenger.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}