package com.nav.car.service;

import com.nav.car.model.Car;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private List<Car> cars;
    private final String FILE_NAME = "cars.csv";

    public CarService() {
        cars = new ArrayList<>();
        readFromDisk();
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void addCar(Car car) {
        car.setCarID(getNextId());
        cars.add(car);
        writeToDisk();
    }

    public void deleteCar(int carID) {
        cars.removeIf(car -> car.getCarID() == carID);
        writeToDisk();
    }

    public Car getCar(int carID) {
        return cars.stream()
                .filter(car -> car.getCarID() == carID)
                .findFirst()
                .orElse(null);
    }

    public void updateCar(int carID, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarID() == carID) {
                updatedCar.setCarID(carID); // Keep original ID
                cars.set(i, updatedCar);
                writeToDisk();
                return;
            }
        }
    }

    // Get the next auto-increment ID
    private int getNextId() {
        if (cars.isEmpty()) {
            return 1;
        }
        return cars.get(cars.size() - 1).getCarID() + 1;
    }

    private void writeToDisk() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Car car : cars) {
                writer.write(car.getCarID() + ","
                        + car.getLicensePlateNumber() + ","
                        + car.getMake() + ","
                        + car.getModel() + ","
                        + car.getYear() + ","
                        + car.getColor() + ","
                        + car.getBodyType() + ","
                        + car.getEngineType() + ","
                        + car.getTransmission());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void readFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("cars.csv file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            cars.clear();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length != 9) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                Car car = new Car();
                car.setCarID(Integer.parseInt(data[0]));
                car.setLicensePlateNumber(data[1]);
                car.setMake(data[2]);
                car.setModel(data[3]);
                car.setYear(Integer.parseInt(data[4]));
                car.setColor(data[5]);
                car.setBodyType(data[6]);
                car.setEngineType(data[7]);
                car.setTransmission(data[8]);
                cars.add(car);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading cars.csv " + e.getMessage());
        }
    }

}
