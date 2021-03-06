package ru.konovalov.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Student {
    @CsvBindByName(column = "ID")
    private int id;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "FAMILY_NAME")
    private String familyName;

}
