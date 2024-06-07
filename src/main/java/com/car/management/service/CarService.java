package com.car.management.service;

import com.car.management.dto.BuySellDto;
import com.car.management.dto.CarDetailsDto;
import com.car.management.exception.CarException;

import java.util.List;

public interface CarService {
    CarDetailsDto addCarDetails(CarDetailsDto carDetailsDto);

    List<CarDetailsDto> getAllRecord() throws CarException;

    CarDetailsDto getrecordById(Integer id);

    CarDetailsDto updateRecordById(Integer id, CarDetailsDto carDetailsDto);

    List<CarDetailsDto> buySellCarRecord(List<BuySellDto> buySellDtos);
}
