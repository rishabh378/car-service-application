package com.learning.services.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Accessory;
import com.learning.entities.Car;
import com.learning.exceldata.reader.AccessoryReader;
import com.learning.exceldata.writer.AccessoryWriter;
import com.learning.repositories.AccessoryRepository;
import com.learning.services.AccessoryService;
import com.learning.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final AccessoryReader accessoryReader;
    private final CarService carService;
    private final AccessoryWriter accessoryWriter;
    private final XSSFWorkbook xssfWorkbook;

    @Override
    public Optional<Accessory> findAccessoryById(Long id) {
        return accessoryRepository.findAll()
                .stream().filter(accessory -> accessory.getId().equals(id)).findFirst();
    }

    @Override
    public List<Accessory> findAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public Optional<Car> findCarByAccessoryId(Long id) {
        Optional<Accessory> optionalAccessory = findAccessoryById(id);
        if (optionalAccessory.isPresent()) {
            Accessory accessory = optionalAccessory.get();
            long carId = accessory.getCarId();
            return carService.findCarById(carId);
        }
        return Optional.empty();
    }

    @Override
    public void createAccessory(Accessory accessory) {
        accessoryRepository.save(accessory);
        log.info("Successfully saved Accessory");
    }

    @Override
    public void updateAccessoryById(Long id, Accessory accessory) {
        if (accessoryRepository.existsById(id)) {
            accessoryRepository.save(accessory);
            log.info(String.format("Successfully update Accessory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.ACCESSORY_NOT_FOUND,id));
        }
    }

    @Override
    public void deleteAccessoryById(Long id) {
        if (accessoryRepository.existsById(id)) {
            accessoryRepository.deleteById(id);
            log.info(String.format("Successfully deleted accessory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.ACCESSORY_NOT_FOUND,id));
        }
    }

    @Override
    public void saveAccessoriesFromExcel() {
        List<Accessory> accessoryList = null;
        try {
            accessoryList = accessoryReader.getAccessoryObjects();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        accessoryRepository.saveAll(accessoryList);
    }

    @Override
    public void writeAccessoriesIntoExcel() {
        try {
            accessoryWriter.createAccessorySheet(xssfWorkbook, findAllAccessories());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
