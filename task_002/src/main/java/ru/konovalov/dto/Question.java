package ru.konovalov.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Question {

    @CsvBindByName(column = "ID")
    private int id;

    @CsvBindByName(column = "QUESTION")
    private String question;
}
