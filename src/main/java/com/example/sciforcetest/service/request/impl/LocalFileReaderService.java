package com.example.sciforcetest.service.request.impl;

import com.example.sciforcetest.dto.ProductDto;
import com.example.sciforcetest.service.request.ReaderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class LocalFileReaderService implements ReaderService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    @Override
    public List<ProductDto> getData(String filePath) {
        return objectMapper.readValue(readJsonData(filePath), new TypeReference<>() {});
    }

    private String readJsonData(String filePath) {
        String result;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            result = bufferedReader.lines().collect(Collectors.joining());
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't find input file " + filePath);
        }
        return result;
    }
}
