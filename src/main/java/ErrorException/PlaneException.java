package ErrorException;

public class PlaneException extends RuntimeException {
    private int number;
    public int getNumber() { return number; }
    public PlaneException(String message, int number) {
        super(message);
        this.number = number;
    }
}
