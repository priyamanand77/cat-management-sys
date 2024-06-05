package com.car.management.repo;

import com.car.management.entity.CarDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailsRepo extends JpaRepository<CarDetailsEntity, Integer> {
}
