package com.apexmotors.carsmanagement.service;

import com.apexmotors.carsmanagement.exception.CarNotFoundException;
import com.apexmotors.carsmanagement.model.Car;
import com.apexmotors.carsmanagement.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car addCar(Car car) {
        return carRepo.save(car);
    }

    public List<Car> findAllCars() {
        return carRepo.findAll();
    }

    public Car findCarById(Long id) {
        return carRepo.findCarById(id).orElseThrow(() -> new CarNotFoundException("Car by id" + id + "was not found"));
    }

    public Car updateCar(Car car) {
        return carRepo.save(car);
    }

    public void deleteCarById(Long id) {
        carRepo.deleteCarById(id);
    }

}
