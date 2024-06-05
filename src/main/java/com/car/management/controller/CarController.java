package com.car.management.controller;

import com.car.management.constants.CarConstants;
import com.car.management.dto.CarDetailsDto;
import com.car.management.dto.GenericResponse;
import com.car.management.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/add-details")
    public ResponseEntity<GenericResponse> addCarDetails(@RequestBody CarDetailsDto carDetailsDto) {
        log.info("class : CarController , method : addCarDetails and CarDetailsDto : {} ", carDetailsDto);
       CarDetailsDto carDetailsDtoRes = carService.addCarDetails(carDetailsDto);

//       GenericResponse genericResponse = new GenericResponse();
//       genericResponse.setStatus(HttpStatus.OK.value());
//       genericResponse.setMessage(CarConstants.SUCCESS);
//       genericResponse.setData(carDetailsDto);

        GenericResponse genericResponse = GenericResponse.builder()
                .status(HttpStatus.OK.value())
                .message(CarConstants.SUCCESS)
                .data(carDetailsDtoRes)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }


}
