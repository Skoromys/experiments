package ru.konovalov.service;

import lombok.NonNull;
import ru.konovalov.dto.Student;

import java.util.List;

public class StudentServiceImpl extends CsvServiceAbstract<Student> implements StudentService {

    @SuppressWarnings("FieldCanBeLocal")
    private final String STUDENT_FILE = "students.csv";

    @Override
    public List<Student> findAll() {
        return findAll(getFilePath(STUDENT_FILE), Student.class);
    }

    @Override
    public Student findByNameAndFamilyName(@NonNull final String name, @NonNull final String familyName) {
        return findAllByFilter(getFilePath(STUDENT_FILE), Student.class,
                (strategy) -> {
                    int nameColumnIdx = strategy.getColumnIndex("NAME");
                    int familyNameColumnIdx = strategy.getColumnIndex("FAMILY_NAME");

                    return (line) -> name.equals(line[nameColumnIdx]) && familyName.equals(line[familyNameColumnIdx]);
                }).stream().findAny().orElse(null);
    }
}
