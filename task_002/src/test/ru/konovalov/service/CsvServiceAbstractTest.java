package ru.konovalov.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.konovalov.helper.ForTestDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CsvServiceAbstractTest extends CsvServiceAbstract {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File testTempFile;
    private final List<ForTestDto> testContentList = Arrays.asList(
            new ForTestDto("str1", -123),
            new ForTestDto("_a#$%!_", 999)
    );
    private CsvServiceAbstract csvService = this;


    @Before
    public void init() throws IOException {
        String testContentHeader = String.join(";", "STR_VALUE", "INT_VALUE");
        String testContentData = testContentList.stream()
                .map(dto -> String.join(";", dto.getStrField(), String.valueOf(dto.getIntField())))
                .collect(Collectors.joining("\n"));

        temporaryFolder.create();
        testTempFile = temporaryFolder.newFile("test_file.csv");
        Files.write(testTempFile.toPath(), Arrays.asList(testContentHeader, testContentData));
    }

    @After
    public void clean() {
        temporaryFolder.delete();
    }


    @Test
    public void findAll() {
        @SuppressWarnings("unchecked")
        List<ForTestDto> actualList = csvService.findAll(testTempFile.toPath(), ForTestDto.class);
        assertArrayEquals(testContentList.toArray(), actualList.toArray());
    }

    @Test
    public void findAllByFilter() {
        @SuppressWarnings("unchecked")
        List<ForTestDto> actual = csvService.findAllByFilter(testTempFile.toPath(), ForTestDto.class,
                (Function<HeaderColumnNameMappingStrategy<ForTestDto>, Predicate<String[]>>) strategy -> {
                    int columnIndex = strategy.getColumnIndex("INT_VALUE");
                    return line -> Integer.valueOf(line[columnIndex]) > 0;
                });
        assertEquals(1, actual.size());
        assertEquals("_a#$%!_", actual.get(0).getStrField());
        assertEquals(999, actual.get(0).getIntField());
    }

}
