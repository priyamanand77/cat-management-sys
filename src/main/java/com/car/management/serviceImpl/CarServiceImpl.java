package com.car.management.serviceImpl;

import com.car.management.dto.CarDetailsDto;
import com.car.management.entity.CarDetailsEntity;
import com.car.management.repo.CarDetailsRepo;
import com.car.management.service.CarService;
import com.car.management.utility.CarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarUtils carUtils;
    @Autowired
    private CarDetailsRepo carDetailsRepo;


    @Override
    public CarDetailsDto addCarDetails(CarDetailsDto carDetailsDto) {
        try {
            log.info("class : CarServiceImpl , method : addCarDetails and data : {} ", carDetailsDto);
            CarDetailsEntity carDetailsEntity = carUtils.toEntity(carDetailsDto, CarDetailsEntity.class);
            CarDetailsEntity savedCarDetails = carDetailsRepo.save(carDetailsEntity);
            return carUtils.toDto(savedCarDetails, CarDetailsDto.class);
        } catch (Exception e) {
            log.error("error while saving into db : {}", e);
            throw new RuntimeException("error while saving into db");
        }
    }


}
