package ru.konovalov.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Student;

import java.util.List;

@Service
@PropertySource("classpath:file_name.properties")
public class StudentServiceImpl extends CsvServiceAbstract<Student> implements StudentService {

    private final String studentFile;

    public StudentServiceImpl(@Value("${file.student}") final String studentFile) {
        this.studentFile = studentFile;
    }

    @Override
    public List<Student> findAll() {
        return findAll(getFilePath(studentFile), Student.class);
    }

    @Override
    public Student findByNameAndFamilyName(@NonNull final String name, @NonNull final String familyName) {
        return findAllByFilter(getFilePath(studentFile), Student.class,
                (strategy) -> {
                    int nameColumnIdx = strategy.getColumnIndex("NAME");
                    int familyNameColumnIdx = strategy.getColumnIndex("FAMILY_NAME");

                    return (line) -> name.equals(line[nameColumnIdx]) && familyName.equals(line[familyNameColumnIdx]);
                }).stream().findAny().orElse(null);
    }
}
