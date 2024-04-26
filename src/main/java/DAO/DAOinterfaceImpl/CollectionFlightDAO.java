package DAO.DAOinterfaceImpl;

import DAO.DAOinterface.FlightDAO;
import ErrorException.FlightException;
import entity.Flight;
import entity.Plane;
import entity.enums.AviaCompany;
import entity.enums.City;
import entity.enums.PlaneModel;
import org.jetbrains.annotations.NotNull;
import utils.general.Shared;

import java.sql.Time;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionFlightDAO implements FlightDAO {

    private Set<Flight> flights;
    private static int zero = 0;
    public static int randomFlightsAmount = 10000;

    public CollectionFlightDAO() {
        this.flights = new HashSet<>();
    }

    @Override
    public Set<Flight> getAllFlights() {
        return this.flights;
    }

    @Override
    public void setFlights(Set<Flight> flights) {
        this.flights = new HashSet<>(flights);
    }


    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        List<Flight> requiredFlight = this.flights.stream()
                .filter(flight ->
                        (flight.getFlightNumber()).equals(flightNumber))
                .toList();
        if (requiredFlight.isEmpty()) throw new FlightException(flightNumber);
        return requiredFlight.get(0);
    }

    @Override
    public String getSpecificFlightDetails(Flight flight) {
        return flight.getAllFlightDetails();
    }

    @Override
    public boolean deleteFlight(String flightNumber) {
        List<Flight> requiredFlight = this.flights.stream()
                .filter(flight ->
                        (flight.getFlightNumber()).equals(flightNumber))
                .toList();
        if (requiredFlight.isEmpty()) throw new FlightException(flightNumber);
        flights.remove(requiredFlight.get(0));
        return false;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        flights.remove(flight);
        return false;
    }

    @Override
    public void saveFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public int getAllSeats(Flight flight) {
        return flight.getSeats();
    }

    @Override
    public int getAvailableSeats(Flight flight) {
        return flight.getAvailableSeats();
    }

    @Override
    public int getBookedSeats(Flight flight) {
        return flight.getBookedSeats();
    }

    @Override
    public void addFlight(Flight flight) {
        saveFlight(flight);
    }

    @Override
    public void displayAllFlights(@NotNull List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    @Override
    public Set<Flight> generateRandomFlights() {
        Set<Flight> randomFlights = new HashSet<>();
        int year = Shared.getCurrentYear();
        Month month = Shared.getCurrentMonth();
        Flight fl;
        while (randomFlights.size() < randomFlightsAmount) {
            int day = Shared.getRandomDay();
            int hrs = Shared.generateRandomNumber(24);
            int min = Shared.generateRandomNumber(59);
            int hrsDuration = Shared.generateRandomNumberMinMax(1, 5);
            int minDuration = Shared.generateRandomNumber(59);
            fl = new Flight(
                    AviaCompany.values()[Shared.generateRandomNumber(AviaCompany.values().length - 1)],
                    new Date(year, month.getValue(), day, hrs, min, zero),
                    new Time(hrsDuration, minDuration, zero),
                    City.values()[Shared.generateRandomNumber(City.values().length - 1)],
                    new Plane(PlaneModel.values()[Shared.generateRandomNumber(PlaneModel.values().length - 1)])
            );
            randomFlights.add(fl);
        }
        this.flights = new HashSet<>();
        this.flights.addAll(randomFlights);
        return this.flights;
    }

    @Override
    public void loadData(Set flights) {
        this.setFlights(flights);
    }
}
