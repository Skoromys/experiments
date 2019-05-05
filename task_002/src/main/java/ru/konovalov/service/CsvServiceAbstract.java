package ru.konovalov.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class CsvServiceAbstract<T> implements CsvService<T> {
    List<T> findAll(@NonNull final Path filePath, @NonNull final Class<T> clazz) {
        List<T> entityList = Collections.emptyList();
        HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(clazz);


        try (Reader reader = Files.newBufferedReader(filePath)) {

            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            CsvToBean<T> csvToBean = csvToBeanBuilder.withType(clazz)
                    .withSeparator(';')
                    .withMappingStrategy(mappingStrategy)
                    .build();

            entityList = csvToBean.parse();
        } catch (IOException e) {
            log.error("Error with csv file " + filePath, e);
        }


        return entityList;
    }

    @Override
    public List<T> findAllByFilter(final Path filePath, Class<T> clazz,
                                   Function<HeaderColumnNameMappingStrategy<T>, Predicate<String[]>> filter) {
        List<T> entityList = Collections.emptyList();
        HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(clazz);

        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = Files.newBufferedReader(filePath)) {

            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            CsvToBean<T> csvToBean = csvToBeanBuilder.withType(clazz)
                    .withSeparator(';')
                    .withMappingStrategy(mappingStrategy)
                    .withFilter((line) -> filter.apply(mappingStrategy).test(line))
                    .build();

            entityList = csvToBean.parse();
        } catch (IOException e) {
            log.error("Error with csv file " + filePath, e);
        }


        return entityList;
    }

    Path getFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile()).toPath();
    }
}
