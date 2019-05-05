package ru.konovalov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Question;

import java.util.List;

@Service
@PropertySource("classpath:file_name.properties")
public class QuestionServiceImpl extends CsvServiceAbstract<Question> implements QuestionService {

    private final String questionFile;

    public QuestionServiceImpl(@Value("${file.question}") final String questionFile) {
        this.questionFile = questionFile;
    }

    @Override
    public List<Question> findAll() {
        return findAll(getFilePath(questionFile), Question.class);
    }
}
