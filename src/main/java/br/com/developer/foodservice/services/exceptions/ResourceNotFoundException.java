package br.com.developer.foodservice.services.exceptions;


public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id, String msg) {
        super(msg + " ID: " + id);
    }
}
