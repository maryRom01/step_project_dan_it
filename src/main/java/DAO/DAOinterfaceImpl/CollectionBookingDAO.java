
package DAO.DAOinterfaceImpl;

import DAO.DAOinterface.BookingDAO;

import entity.Booking;
import utils.Logger;
import utils.fileLoader.FileBookingLoaderBin;

import java.util.*;

/**
 * description
 *
 * @author Alexander Isai on 15.04.2024.
 */
public class CollectionBookingDAO implements BookingDAO {

    private FileBookingLoaderBin bookingLoaderBin;
    private List<Booking> bookings;

    public CollectionBookingDAO() {
        this.bookingLoaderBin = new FileBookingLoaderBin();
        this.bookings = getAllBooking();
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public Booking getBookingById(int id) {
        Optional<Booking> booking = bookings.stream()
                .filter(bk -> bk != null && bk.getId() == id)
                .findFirst();
        Logger.info("Отримано бронювання по ID " + id);
        return booking.orElse(null);
    }

    @Override
    public void saveBooking(Booking booking) {
        if (booking == null){
            Logger.error("Невдала спроба створення бронювання");
        }
        int index = bookings.indexOf(booking);
        if (index >= 0) {
            bookings.set(index, booking);
            Logger.info("Бронювання з ID " + index + " змінено");
        } else {
            bookings.add(booking);
            Logger.info("Бронювання з ID " + booking.getId() + " додано то списку");
        }
        bookingLoaderBin.loadData(bookings);
        Logger.info("Бронювання з ID " + booking.getId() + " успішно збережено в БД");
    }

    @Override
    public void cancelBooking(Booking booking) {
        if (booking == null){
            Logger.error("Невдала спроба видалення бронювання");
        } else {
            bookings.remove(booking);
            Logger.info("Бронювання з ID " + booking.getId() + " видалено зі списку");
            bookingLoaderBin.loadData(bookings);
            Logger.info("Бронювання з ID " + booking.getId() + " видалено з БД");
        }
    }

    @Override
    public List<Booking> getAllBooking() {
        Logger.info("Отриманий список бронювань");
        return bookingLoaderBin.getDataFromFile();
    }

}
