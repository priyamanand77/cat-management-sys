package com.car.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDetailsDto {
    private Integer cId;
    private String name;
    private String model;
    private Integer units;
}
