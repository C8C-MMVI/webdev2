package com.nav.car;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbTester implements CommandLineRunner {

    private final CarRepository carRepository;

    public DbTester(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car car = new Car();
        car.setColor("Turquoise");
        car.setMake("Caddy");
        car.setModel("Cadillac");
        car.setYear(1955);

        carRepository.save(car);
        carRepository.findAll().forEach(System.out::println);
    }
}
