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
        List<Car> cars = carRepo.findAll();
        if (cars.isEmpty()) {
            throw new CarNotFoundException("Car not found");
        } else {
            return cars;
        }
    }

    public Car findCarById(Long id) {
        if (carRepo.existsById(id)) {
            return carRepo.findCarById(id);
        }
        else {
            throw new CarNotFoundException("Car not found");
        }
    }

    public Car updateCar(Car currentCar, Car updatedCar) {
        Car result = null;
        if (currentCar != null && updatedCar != null) {
            if (validateAttribute(updatedCar.getManufacturer())) {
                currentCar.setManufacturer(updatedCar.getManufacturer());
            }
            if (validateAttribute(updatedCar.getModel())) {
                currentCar.setModel(updatedCar.getModel());
            }
            if (validateAttribute(updatedCar.getColor())) {
                currentCar.setColor(updatedCar.getColor());
            }
            if (validateAttribute(updatedCar.getAmount())) {
                currentCar.setAmount(updatedCar.getAmount());
            }
            if (validateAttribute(updatedCar.getPrice())) {
                currentCar.setPrice(updatedCar.getPrice());
            }
            if (validateAttribute(updatedCar.getDescription())) {
                currentCar.setDescription(updatedCar.getDescription());
            }
            if (validateAttribute(updatedCar.getYear())) {
                currentCar.setYear(updatedCar.getYear());
            }
            if (validateAttribute(updatedCar.getImageURL())) {
                currentCar.setImageURL(updatedCar.getImageURL());
            }
        }
        if (currentCar != null) {
            result = carRepo.save(currentCar);
        }
        return result;
    }

    public void decrementCarAmount(Car currentCar) {
        if (currentCar.getAmount() > 0) {
            currentCar.setAmount(currentCar.getAmount() - 1);
        }
        carRepo.save(currentCar);
    }

    public void deleteCarById(Long id) {
        carRepo.deleteById(id);
    }

    public Boolean validateAttribute(String attribute) {
        return attribute != null && !attribute.isEmpty();
    }

    public Boolean validateAttribute(Double attribute) {
        return attribute != null;
    }
    public Boolean validateAttribute(Integer attribute) {
        return attribute != null;
    }
}
