package ru.konovalov.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Answer {

    @CsvBindByName(column = "ID")
    private int id;

    @CsvBindByName(column = "QUESTION_ID")
    private int questionId;

    @CsvBindByName(column = "IS_CORRECT")
    private byte isCorrect;

    @CsvBindByName(column = "VALUE")
    private String value;

}
