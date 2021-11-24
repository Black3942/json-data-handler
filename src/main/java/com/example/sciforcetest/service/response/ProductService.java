package com.example.sciforcetest.service.response;

import com.example.sciforcetest.dto.ProductAvailabilityDto;
import com.example.sciforcetest.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> get();

    List<ProductDto> getAll();

    List<ProductAvailabilityDto> getAvailability();
}
