package com.learning.services;

import com.learning.entities.Accessory;
import com.learning.entities.Car;

import java.util.List;
import java.util.Optional;

public interface AccessoryService {

    Optional<Accessory> findAccessoryById(Long id);
    List<Accessory> findAllAccessories();
    Optional<Car> findCarByAccessoryId(Long id);
    void createAccessory(Accessory accessory);
    void updateAccessoryById(Long id, Accessory accessory);
    void deleteAccessoryById(Long id);
    void saveAccessoriesFromExcel();
    void writeAccessoriesIntoExcel();
}
