package com.learning.entities;

import com.learning.enums.InventoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private Long id;
    private String location;
    @Enumerated(EnumType.STRING)
    private InventoryType type;
}
