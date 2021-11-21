package com.example.sciforcetest.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    private static final String url = "https://sciforce.solutions/downloads/sciforce-java-test1.json";
    private static final String filePath = "D:\\input.json";
    private static final List<String> dataSourceList = List.of(
            "https://sciforce.solutions/downloads/sciforce-java-test1.json",
            "https://sciforce.solutions/downloads/sciforce-java-test2.json",
            "D:\\input1.json",
            "D:\\input2.json"
    );

    public String getUrl() {
        return url;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<String> getDataSourceList() {
        return dataSourceList;
    }
}
