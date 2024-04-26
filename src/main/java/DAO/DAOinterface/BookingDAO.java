
package DAO.DAOinterface;


import entity.Booking;

import java.util.List;

/**
 * It is an interface for operations related to flight search and ticket booking.
 * @author Alexander Isai on 14.04.2024.
 */
public interface BookingDAO {


    Booking getBookingById(int id);
    void saveBooking(Booking booking);
    void cancelBooking(Booking booking);
    List<Booking> getAllBooking();
}
