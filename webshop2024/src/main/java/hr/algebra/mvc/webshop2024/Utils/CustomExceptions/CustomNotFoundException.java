package hr.algebra.mvc.webshop2024.Utils.CustomExceptions;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String message) {
        super(message);
    }
}