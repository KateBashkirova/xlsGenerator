package customExeptions;

public class ExceedingLineLimitException extends Exception {
    public ExceedingLineLimitException(String message) {
        super(message);
    }
}
