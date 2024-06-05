package com.car.management.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarUtils {

    @Autowired
    private ModelMapper mapper;


//    private CarDetailsEntity gerCarDetailsEntity(CarDetailsDto carDetailsDto)
//    {
//       return CarDetailsEntity.builder()
//                .name(carDetailsDto.getName())
//                .model(carDetailsDto.getModel())
//                .units(carDetailsDto.getUnits())
//                .build();
//    }
//
//    private CarDetailsEntity gerCarDetailsEntityNew(CarDetailsDto carDetailsDto)
//    {
//        CarDetailsEntity carDetailsEntity = new CarDetailsEntity();
//        BeanUtils.copyProperties(carDetailsDto,carDetailsEntity);
//        return carDetailsEntity;
//    }
//
//    private CarDetailsEntity gerCarDetailsEntityNew2(CarDetailsDto carDetailsDto) {
//        ModelMapper mapper = new ModelMapper();
//return  mapper.map(carDetailsDto, CarDetailsEntity.class);
//
//    }

    public <T> T toEntity(Object dto, Class<T> entity) {
        return mapper.map(dto, entity);
    }

    public <T> T toDto(Object entity, Class<T> dto) {
        return mapper.map(entity, dto);
    }
}
