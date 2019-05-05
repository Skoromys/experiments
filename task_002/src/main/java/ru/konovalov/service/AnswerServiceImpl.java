package ru.konovalov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Answer;

import java.util.List;
import java.util.Objects;

@Service
@PropertySource("classpath:file_name.properties")
public class AnswerServiceImpl extends CsvServiceAbstract<Answer> implements AnswerService {

    private final String answerFile;

    public AnswerServiceImpl(@Value("${file.answer}") final String fileName) {
        this.answerFile = fileName;
    }

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        return findAllByFilter(getFilePath(answerFile), Answer.class,
                (strategy) -> {
                    int columnIndex = strategy.getColumnIndex("QUESTION_ID");
                    return line -> Objects.equals(line[columnIndex], String.valueOf(questionId));
                });
    }
}
