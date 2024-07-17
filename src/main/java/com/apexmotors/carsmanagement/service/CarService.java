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
            if (updatedCar.getManufacturer() != null) {
                currentCar.setManufacturer(updatedCar.getManufacturer());
            }
            if (updatedCar.getModel() != null) {
                currentCar.setModel(updatedCar.getModel());
            }
            if (updatedCar.getColor() != null) {
                currentCar.setColor(updatedCar.getColor());
            }
            if (updatedCar.getAmount() != 0.0) {
                currentCar.setAmount(updatedCar.getAmount());
            }
            if (updatedCar.getPrice() != 0.0) {
                currentCar.setPrice(updatedCar.getPrice());
            }
            if (updatedCar.getDescription() != null) {
                currentCar.setDescription(updatedCar.getDescription());
            }
            if (updatedCar.getYear() != 0) {
                currentCar.setYear(updatedCar.getYear());
            }
            if (updatedCar.getImageURL() != null) {
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
}
