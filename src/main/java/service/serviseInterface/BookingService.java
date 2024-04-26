package service.serviseInterface;

import dto.BookingFlightDTO;
import entity.Booking;
import entity.Flight;
import entity.Passenger;

import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 16.04.2024.
 */
public interface BookingService {

    void createNewBooking(BookingFlightDTO bookingFlightDTO);
    List<Booking> getAllBookingByPassenger(Passenger passenger);
    <T> void displayItems(List<T> items);
    void cancelBooking(int id);
    List<Passenger> getAllPassengers();
    Passenger getActivePassenger(String name, String surname, String password);
    List<Booking> getAllBookings();
    Booking getBookingById(int id);
    Flight getFlightByBookingId(int id);
    List<Integer> getBookingsIdsByPassenger(Passenger passenger);

}
