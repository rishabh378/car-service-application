package com.learning.models;

import com.learning.enums.InventoryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {

    private InventoryRequest inventoryRequest;
    private CarRequest carRequest;
    private AccessoryRequest accessoryRequest;

    @Data
    @Builder
    public static class InventoryRequest {
        private Long id;
        private String location;
        private InventoryType type;
    }
    @Data
    @Builder
    public static class CarRequest {
        private Long id;
        private String name;
        private String brand;
        private Integer modelNo;
        private Long inventoryId;
    }
    @Data
    @Builder
    public static class AccessoryRequest {
        private Long id;
        private String name;
        private Double price;
        private Long carId;
    }
}
