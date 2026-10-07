package com.priyanka.productcatalogservice.exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String message) {
        super(message);
    }
}
