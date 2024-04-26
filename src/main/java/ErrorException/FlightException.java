package ErrorException;

public class FlightException extends RuntimeException {
    public FlightException(String message) {
        super(String.format("Немає рейсу за таким номером = %s", message));
    }
}
