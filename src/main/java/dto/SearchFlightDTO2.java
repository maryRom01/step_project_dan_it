package dto;

import java.util.Date;

public class SearchFlightDTO2 {

    private String destination2;
    private Date date2;
    private int passQuantity2;

    public SearchFlightDTO2(String destination, Date date, int passQuantity) {
        this.destination2 = destination;
        this.date2 = date;
        this.passQuantity2 = passQuantity;
    }

    public String getDestination() {
        return destination2;
    }

    public Date getDate() {
        return date2;
    }

    public int getPassQuantity() {
        return passQuantity2;
    }

    @Override
    public String toString() {
        return "SearchFlightDTO{" +
                "destination='" + destination2 + '\'' +
                ", date=" + date2 +
                ", passQuantity=" + passQuantity2 +
                '}';
    }
}
