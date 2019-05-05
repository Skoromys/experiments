package ru.konovalov.helper;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ForTestDto {
    @CsvBindByName(column = "STR_VALUE")
    private String strField;
    @CsvBindByName(column = "INT_VALUE")
    private int intField;
}
