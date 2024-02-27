package hr.algebra.mvc.webshop2024.Utils.CustomExceptions;

public class CustomUnauthorizedException extends RuntimeException {

    public CustomUnauthorizedException() {
        super();
    }

    public CustomUnauthorizedException(String message) {
        super(message);
    }

    public CustomUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomUnauthorizedException(Throwable cause) {
        super(cause);
    }
}