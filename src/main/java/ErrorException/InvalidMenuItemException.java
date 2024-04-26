package ErrorException;

public class InvalidMenuItemException extends Exception {
    public InvalidMenuItemException(String message) {
        super("\u001B[31m" + message + "\u001B[0m");
    }
}
