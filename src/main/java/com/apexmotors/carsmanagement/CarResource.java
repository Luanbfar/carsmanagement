package com.apexmotors.carsmanagement;

import com.apexmotors.carsmanagement.exception.CarNotFoundException;
import com.apexmotors.carsmanagement.model.Car;
import com.apexmotors.carsmanagement.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarResource {
    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        ResponseEntity<List<Car>> response;
        try {
            List<Car> cars = carService.findAllCars();
            response = new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (CarNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        ResponseEntity<Car> response;
        try {
            Car car = carService.findCarById(id);
            response = new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        ResponseEntity<Car> response;
        try {
            Car carAdded = null;
            if (car != null) {
                carAdded = carService.addCar(car);
            }
            response = new ResponseEntity<>(carAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        ResponseEntity<Car> response;
        try {
            Car existingCar = carService.findCarById(id);
            if (existingCar != null) {
                existingCar.setAmount(updatedCar.getAmount());
                existingCar.setColor(updatedCar.getColor());
                existingCar.setDescription(updatedCar.getDescription());
                existingCar.setPrice(updatedCar.getPrice());
                existingCar.setYear(updatedCar.getYear());
            }
            Car updatedCarResult = carService.updateCar(existingCar);
            response = new ResponseEntity<>(updatedCarResult, HttpStatus.OK);
        } catch (CarNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/buy/{id}")
    public ResponseEntity<?> buyCar(@PathVariable Long id) {
        ResponseEntity<?> response;
        try {
            Car car = carService.findCarById(id);
            carService.decrementCarAmount(car);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        try {
            carService.deleteCarById(id);
        } catch (CarNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
