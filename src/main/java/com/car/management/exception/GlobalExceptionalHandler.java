package com.car.management.exception;

import com.car.management.constants.CarConstants;
import com.car.management.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {


    @ExceptionHandler(CarException.class)
    public ResponseEntity<GenericResponse> sendErrorMsg(CarException e) {


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(CarConstants.FAILED)
                .data(e.getMessage())
                .build());
    }


    @ExceptionHandler(InsufficientCar.class)
    public ResponseEntity<GenericResponse> sendErrorMsgInsufficientCar(InsufficientCar e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(CarConstants.FAILED)
                .data(e.getMessage())
                .build());
    }
}
