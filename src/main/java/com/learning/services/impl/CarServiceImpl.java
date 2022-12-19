package com.learning.services.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Car;
import com.learning.entities.Inventory;
import com.learning.exceldata.reader.CarReader;
import com.learning.exceldata.writer.CarWriter;
import com.learning.exceptions.CarNotFoundException;
import com.learning.repositories.CarRepository;
import com.learning.services.CarService;
import com.learning.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarReader carReader;
    private final InventoryService inventoryService;
    private final CarWriter carWriter;
    private final XSSFWorkbook xssfWorkbook;

    @Override
    public Optional<Car> findCarById(Long id) {
        return carRepository.findAll()
                .stream().filter(car -> car.getId().equals(id)).findFirst();
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Inventory> findInventoryByCarId(Long id) {
        return inventoryService.findInventoryById((findCarById(id))
                .orElseThrow(() -> new CarNotFoundException(String.format(ExceptionMessage.CAR_NOT_FOUND)))
                .getInventoryId());
    }

    @Override
    public void createCar(Car car) {
        carRepository.save(car);
        log.info("Successfully saved Car");
    }

    @Override
    public void updateCarById(Long id, Car car) {
        if (carRepository.existsById(id)) {
            carRepository.save(car);
            log.info(String.format("Successfully update car with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.CAR_NOT_FOUND, id));
        }
    }

    @Override
    public void deleteCarById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            log.info(String.format("Successfully deleted Car with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.CAR_NOT_FOUND));
        }
    }

    @Override
    public void saveCarsFromExcel() {
        List<Car> carList = null;
        try {
            carList = carReader.getCarObjects();
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
        carRepository.saveAll(carList);

    }

    @Override
    public void writeCarsIntoExcel() {
        try {
            carWriter.createCarsheet(xssfWorkbook, findAllCars());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
