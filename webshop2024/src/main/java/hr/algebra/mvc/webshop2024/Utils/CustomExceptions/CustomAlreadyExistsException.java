package hr.algebra.mvc.webshop2024.Utils.CustomExceptions;

public class CustomAlreadyExistsException extends RuntimeException{
    public CustomAlreadyExistsException(String message) {
        super(message);
    }
}