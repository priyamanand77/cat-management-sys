package com.car.management.exception;

public class InsufficientCar extends RuntimeException{
    public InsufficientCar() {
    }

    public InsufficientCar(String message) {
        super(message);
    }

    public InsufficientCar(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientCar(Throwable cause) {
        super(cause);
    }

    public InsufficientCar(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
