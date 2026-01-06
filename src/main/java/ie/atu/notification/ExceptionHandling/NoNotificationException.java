package ie.atu.notification.ExceptionHandling;

public class NoNotificationException extends RuntimeException {


    public NoNotificationException(String message) {
        super(message);
    }
}