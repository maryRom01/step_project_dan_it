package entity;

import ErrorException.PlaneException;
import entity.enums.PlaneModel;
import utils.Logger;
import utils.general.Shared;

import java.io.Serializable;

public class Plane implements Serializable {

    public static final int defaultSeatsAmount = 20;
    public static final String errorAmountShouldBeGreaterThan0 = "Кількість місць має бути > 0";
    public static final String errorNotEnoughFreeSeats = "Недостатньо вільних місць";
    private PlaneModel planeModel;
    private int seats;
    private int bookedSeats;

    public Plane(PlaneModel planeModel) {
        this.seats = planeModel.getSeats();
        if (this.seats < 0) throw new PlaneException(errorAmountShouldBeGreaterThan0, seats);
        this.bookedSeats = getRandomBookedSeats();
    }

    public Plane() {
        this.seats = defaultSeatsAmount;
        this.bookedSeats = 0;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    private int getRandomBookedSeats() {
        int choice = Shared.generateRandomNumberMinMax(0, 3);
        if (choice < 2) {
            return Shared.generateRandomNumberMinMax(0, seats / 3);
        } else return 0;
    }

    public int getSeats() {
        return seats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getAvailableSeats() {
        return seats - bookedSeats;
    }

    public int getDefaultSeatsAmount() {
        return defaultSeatsAmount;
    }

    public void setBookedSeats(int requestedSeats) {
        int freeSeats = getAvailableSeats();
        if (requestedSeats <= 0) throw new PlaneException(errorAmountShouldBeGreaterThan0, requestedSeats);
        if (requestedSeats <= freeSeats) {
            this.bookedSeats = requestedSeats;
        } else {
            Logger.error("Невдала спроба змінити місця рейсу");
            throw new PlaneException(errorNotEnoughFreeSeats, freeSeats);
        }
    }

    @Override
    public String toString() {
        return "Plane [ " +
                " seats = " + seats +
                ", bookedSeats = " + bookedSeats +
                ", availableSeats = " + getAvailableSeats() +
                " ] ";
    }

    public void updateSeats(int requiredBookedSeats) {
        if (requiredBookedSeats <= getAvailableSeats())
        this.bookedSeats += requiredBookedSeats;
        else {
            Logger.error("Невдала спроба змінити місця рейсу");
            throw new PlaneException("No available seats in this plane", requiredBookedSeats);
        }
    }
}
