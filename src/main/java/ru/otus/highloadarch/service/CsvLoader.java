package ru.otus.highloadarch.service;

import java.util.List;

public interface CsvLoader {
    List<List<String>> loadFromFile(String path);
}
