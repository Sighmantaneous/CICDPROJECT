package ie.atu.notification.ExceptionHandling;

public class DuplicateException extends RuntimeException {

    private String message;
    private String field;


    public DuplicateException(String message) {

        super(message);
    }
}