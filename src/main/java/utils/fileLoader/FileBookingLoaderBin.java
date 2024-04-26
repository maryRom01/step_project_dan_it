package utils.fileLoader;

import entity.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File for recording bookings in the bin file
 * @author Alexander Isai on 15.04.2024.
 */
public class FileBookingLoaderBin {

    private final File file = new File("booking.bin");

    public FileBookingLoaderBin() {
    }
    public void loadData(List<Booking> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Booking> getDataFromFile() {
        File file = new File("booking.bin");
        List<Booking> bookingList;
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            bookingList = (List<Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return bookingList;
    }
}
