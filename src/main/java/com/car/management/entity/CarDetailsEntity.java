package com.car.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CAR_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDetailsEntity {

    @Id
    @Column(name = "CAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;
    @Column(name = "CAR_NAME" , length = 100)
    private String name;
    @Column(name = "CAR_MODEL")
    private String model;
    @Column(name = "NO_OF_UNITS")
    private Integer units;
}
