package com.nav.agri.service;

import com.nav.agri.dto.CarDTO;
import com.nav.agri.models.Car;
import com.nav.agri.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> findAll(){
        return repository.findAll();
    }

    public Car findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Car save(CarDTO car){
        Car newCar = new Car();
        newCar.setMake(car.getMake());
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        newCar.setColor(car.getColor());
        newCar.setBodyType(car.getBodyType());
        newCar.setEngineType(car.getEngineType());
        newCar.setLicensePlate(car.getLicensePlate());
        return repository.save(newCar);
    }

    public Car updateCar(Car car, CarDTO carDTO){
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setColor(carDTO.getColor());
        car.setBodyType(carDTO.getBodyType());
        car.setEngineType(carDTO.getEngineType());
        car.setLicensePlate(carDTO.getLicensePlate());
        return repository.save(car);
    }

    public void deleteCar(int id){
        repository.deleteById(id);
    }
}
