package com.learning.entities;

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
@Table(name = "accessory")
public class Accessory {
    @Id
    private Long id;
    private String name;
    private Double price;
    private Long carId;

}
