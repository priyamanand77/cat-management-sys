package com.car.management.controller;

import com.car.management.constants.CarConstants;
import com.car.management.dto.BuySellDto;
import com.car.management.dto.CarDetailsDto;
import com.car.management.dto.GenericResponse;
import com.car.management.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse> getAllRecord() {
        log.info("class : CarController , method : getAllRecord");

        List<CarDetailsDto> carDetailsDtos = carService.getAllRecord();
        return ResponseEntity.ok(GenericResponse.builder()
                .status(HttpStatus.OK.value())
                .message(CarConstants.SUCCESS)
                .data(carDetailsDtos)
                .build());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GenericResponse> getById(@PathVariable(name = "id") Integer id) {
        log.info("class : CarController , method : getById");
        CarDetailsDto carDetailsDto = carService.getrecordById(id);
        return ResponseEntity.ok(GenericResponse.builder()
                .status(HttpStatus.OK.value())
                .message(CarConstants.SUCCESS)
                .data(carDetailsDto)
                .build());
    }

    @PutMapping("/update-id/{id}")
    public ResponseEntity<GenericResponse> updateById(@PathVariable(name = "id") Integer id, @RequestBody CarDetailsDto carDetailsDto) {
        log.info("class : CarController , method : getById");
        CarDetailsDto dto = carService.updateRecordById(id,carDetailsDto);
        return ResponseEntity.ok(GenericResponse.builder()
                .status(HttpStatus.OK.value())
                .message(CarConstants.SUCCESS)
                .data(dto)
                .build());
    }


    @PostMapping("/buy-sell")
    public ResponseEntity<GenericResponse> buySellCars(@RequestBody List<BuySellDto> buySellDtos)
    {  log.info("class : CarController , method : buySellCars");
        List<CarDetailsDto> updatedRecord = carService.buySellCarRecord(buySellDtos);
        return ResponseEntity.ok(GenericResponse.builder()
                .status(HttpStatus.OK.value())
                .message(CarConstants.SUCCESS)
                .data(updatedRecord)
                .build());
    }
}
