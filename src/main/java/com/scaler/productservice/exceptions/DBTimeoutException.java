package com.scaler.productservice.exceptions;

public class DBTimeoutException extends Exception{
    public DBTimeoutException(String message){
        super(message);
    }
}
