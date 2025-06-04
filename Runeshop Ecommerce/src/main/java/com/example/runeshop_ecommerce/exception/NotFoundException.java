package com.example.runeshop_ecommerce.exception;

public class NotFoundException extends ApiException {

    public NotFoundException(String mensaje) {
        super(mensaje, "404");
    }
}
