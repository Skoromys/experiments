package ru.konovalov.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CsvService<T> {

    List<T> findAllByFilter(Path filePath,
                            Class<T> clazz,
                            Function<HeaderColumnNameMappingStrategy<T>, Predicate<String[]>> filter);
}
