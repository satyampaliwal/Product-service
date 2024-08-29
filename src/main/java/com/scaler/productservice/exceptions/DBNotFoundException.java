package com.scaler.productservice.exceptions;

public class DBNotFoundException extends Exception{
    public DBNotFoundException(String message){
        super(message);
    }
}
