package com.learning.models;

import com.learning.enums.InventoryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private InventoryResponse inventoryResponse;
    private CarResponse carResponse;
    private AccessoryResponse accessoryResponse;
    @Data
    @Builder
    public static class InventoryResponse {
        private Long id;
        private String location;
        private InventoryType type;
    }
    @Data
    @Builder
    public static class CarResponse {
        private Long id;
        private String name;
        private String brand;
        private Integer modelNo;
        private Long inventoryId;
    }
    @Data
    @Builder
    public static class AccessoryResponse {
        private Long id;
        private String name;
        private Double price;
        private Long carId;
    }
}
