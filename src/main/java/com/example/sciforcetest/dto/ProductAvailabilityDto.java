package com.example.sciforcetest.dto;

import java.util.Map;
import lombok.Data;

@Data
public class ProductAvailabilityDto {
    private String productUuid;
    private String productName;
    private Integer amount;
    private Map<String, Integer> availability;

    public ProductAvailabilityDto() {
    }

    public ProductAvailabilityDto(ProductDto productDto) {
        this.productUuid = productDto.getProductUuid();
        this.productName = productDto.getProductName();
        this.amount = productDto.getAmount();
    }
}
