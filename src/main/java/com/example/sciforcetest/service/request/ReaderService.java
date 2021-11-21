package com.example.sciforcetest.service.request;

import com.example.sciforcetest.dto.ProductDto;
import java.util.List;

public interface ReaderService {
    List<ProductDto> getData(String link);
}
