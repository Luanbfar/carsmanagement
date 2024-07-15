package com.apexmotors.carsmanagement.repo;

import com.apexmotors.carsmanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    Car findCarById(Long id);
}
