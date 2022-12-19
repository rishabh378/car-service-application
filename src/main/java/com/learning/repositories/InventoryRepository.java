package com.learning.repositories;

import com.learning.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository  extends JpaRepository<Inventory, Long> {

}
