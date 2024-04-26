package dto;

import utils.DateConverter;

import java.util.Objects;

/**
 * This is a DTO file for transferring information
 * from the user (destination, date, number of people)
 * to the matching flight search service.
 * @author Alexander Isai on 14.04.2024.
 */
public class SearchFlightDTO {

    private String destination;
    private long date;
    private int passQuantity;

    public SearchFlightDTO(String destination, String date, int passQuantity) {
        this.destination = destination;
        this.date = DateConverter.stringToLong(date);
        this.passQuantity = passQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchFlightDTO that = (SearchFlightDTO) o;
        return date == that.date && passQuantity == that.passQuantity && Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, date, passQuantity);
    }

    @Override
    public String toString() {
        return "SearchFlightDTO{" +
                "destination='" + destination + '\'' +
                ", date=" + date +
                ", passQuantity=" + passQuantity +
                '}';
    }
}
