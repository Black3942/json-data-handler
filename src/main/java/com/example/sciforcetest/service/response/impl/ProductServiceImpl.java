package com.example.sciforcetest.service.response.impl;

import com.example.sciforcetest.config.DataSourceConfig;
import com.example.sciforcetest.dto.ProductDto;
import com.example.sciforcetest.service.request.ReaderService;
import com.example.sciforcetest.service.request.impl.HttpClientReaderService;
import com.example.sciforcetest.service.request.impl.LocalFileReaderService;
import com.example.sciforcetest.service.response.ProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final DataSourceConfig config;
    private final HttpClientReaderService httpReader;
    private final LocalFileReaderService fileReader;

    public ProductServiceImpl(DataSourceConfig config, HttpClientReaderService httpReader,
                              LocalFileReaderService fileReader) {
        this.config = config;
        this.httpReader = httpReader;
        this.fileReader = fileReader;
    }

    @Override
    public List<ProductDto> get() {
        Map<String, Integer> amounts = new HashMap<>();
        Map<String, String> names = new HashMap<>();
        List<ProductDto> list = httpReader.getData(config.getUrl());
        list.forEach(dto -> {
            amounts.merge(dto.getProductUuid(), dto.getAmount(), Integer::sum);
            names.put(dto.getProductUuid(), dto.getProductName());
        });
        List<ProductDto> listTwo = fileReader.getData(config.getFilePath());
        return getProducts(listTwo, amounts, names);
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> list = new ArrayList<>();
        List<String> links = config.getDataSourceList();
        links.forEach(link -> {
            list.addAll(getDataSourceImpl(link).getData(link));
        });
        Map<String, Integer> amounts = new HashMap<>();
        Map<String, String> names = new HashMap<>();
        return getProducts(list, amounts, names);
    }

    private List<ProductDto> getProducts(List<ProductDto> list,
                                            Map<String, Integer> amounts,
                                            Map<String, String> names) {
        list.forEach(dto -> {
            amounts.merge(dto.getProductUuid(), dto.getAmount(), Integer::sum);
            names.put(dto.getProductUuid(), dto.getProductName());
        });
        List<ProductDto> resultList = new ArrayList<>();
        amounts.forEach((key, value) -> {
            ProductDto dto = new ProductDto();
            dto.setProductUuid(key);
            dto.setProductName(names.get(key));
            dto.setAmount(value);
            resultList.add(dto);
        });
        return resultList;
    }

    private ReaderService getDataSourceImpl(String link) {
        String protocol = link.split(":")[0];
        if (protocol.equals("http") || protocol.equals("https")) {
            return httpReader;
        } else {
            return fileReader;
        }
    }
}
