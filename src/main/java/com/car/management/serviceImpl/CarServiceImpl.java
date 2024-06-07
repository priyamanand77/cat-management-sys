package com.car.management.serviceImpl;

import com.car.management.dto.BuySellDto;
import com.car.management.dto.CarDetailsDto;
import com.car.management.entity.CarDetailsEntity;
import com.car.management.exception.CarException;
import com.car.management.exception.InsufficientCar;
import com.car.management.repo.CarDetailsRepo;
import com.car.management.service.CarService;
import com.car.management.utility.CarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
            throw new CarException("error while saving into db");
        }
    }

    @Override
    public List<CarDetailsDto> getAllRecord() {
        try {
            log.info("class : CarServiceImpl , method : getAllRecord");
            List<CarDetailsEntity> all = carDetailsRepo.findAll();
            // for loop
//            for (int i = 0; i < all.size(); i++) {
//                CarDetailsDto dto = carUtils.toDto(all.get(i), CarDetailsDto.class);
//                carDetailsDtos.add(dto);
//            }

            //enhanced for loop
//            for(CarDetailsEntity c : all)
//            {
//                CarDetailsDto dto = carUtils.toDto(c, CarDetailsDto.class);
//                carDetailsDtos.add(dto);
//            }
            //stream api
            return all.stream().map(e -> carUtils.toDto(e, CarDetailsDto.class)).toList();
        } catch (Exception e) {
            log.error("error while fetching ", e);
            throw new CarException("error while fetching");
        }
    }

    @Override
    public CarDetailsDto getrecordById(Integer id) {
        try {
            log.info("class : CarServiceImpl , method : getrecordById");
            CarDetailsEntity carDetailsEntity = carDetailsRepo.findById(id).orElseThrow();
            return carUtils.toDto(carDetailsEntity, CarDetailsDto.class);
        } catch (Exception e) {
            log.error("error while fetching data : ", e);
            throw new CarException("error while fetching data");
        }
    }

    @Override
    public CarDetailsDto updateRecordById(Integer id, CarDetailsDto newCar) {

        CarDetailsEntity oldCar = carDetailsRepo.findById(id).orElseThrow(() -> new CarException("error while fetching data"));
        try {
            log.info("class : CarServiceImpl , method : updateRecordById , carDetailsDto : {}", newCar);

            if (newCar.getName() == null) {
                newCar.setName(oldCar.getName());
            }
            if (newCar.getModel() == null) {
                newCar.setModel(oldCar.getModel());
            }
            if (newCar.getUnits() == null || newCar.getUnits() == 0) {
                newCar.setUnits(oldCar.getUnits());
            }
            newCar.setCId(id);
            CarDetailsEntity entity = carUtils.toEntity(newCar, CarDetailsEntity.class);
            CarDetailsEntity save = carDetailsRepo.save(entity);
            return carUtils.toEntity(save, CarDetailsDto.class);

        } catch (Exception e) {
            log.error("error while fetching data : ", e);
            throw new CarException("error while fetching data");
        }
    }


    private BuySellDto containsInList(List<BuySellDto> buySellDtos, CarDetailsEntity carDetails) {
        return buySellDtos.stream().filter(e -> {
            if (Objects.equals(e.getId(), carDetails.getCId())) return true;
            return false;
        }).toList().get(0);
    }

    @Override
    public List<CarDetailsDto> buySellCarRecord(List<BuySellDto> buySellDtos) {
        log.info("class : CarServiceImpl , method : updateRecordById , buySellDtos : {}", buySellDtos);
        try {

            List<Integer> allIds = buySellDtos.stream().map(BuySellDto::getId).toList();
            List<CarDetailsEntity> all = carDetailsRepo.findAllById(allIds);

            List<CarDetailsEntity> list = all.stream().map(e -> {
                BuySellDto buySellDto = containsInList(buySellDtos, e);
                if (buySellDto.getAction().equals("S")) {
                    e.setUnits(e.getUnits() - buySellDto.getUnits());
                } else if (buySellDto.getAction().equals("B")) {
                    e.setUnits(e.getUnits() + buySellDto.getUnits());
                }

                if (e.getUnits() < 0) {
                    throw new InsufficientCar("In-sufficient car in garage");
                }
                return e;
            }).toList();
            carDetailsRepo.saveAll(list);
            return list.stream().map(f -> carUtils.toDto(f, CarDetailsDto.class)).toList();

        } catch (CarException e) {
            log.error("Error While Updating :", e);
            throw new CarException("Error While Updating");
        }
    }


}
