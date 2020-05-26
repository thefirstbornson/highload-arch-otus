package ru.otus.highloadarch.service;

import liquibase.util.csv.CSVReader;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CsvLoaderImpl implements CsvLoader {
    @Override
    @SneakyThrows
    public List<List<String>> loadFromFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream(path))))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}
