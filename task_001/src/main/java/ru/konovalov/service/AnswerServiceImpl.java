package ru.konovalov.service;

import ru.konovalov.dto.Answer;

import java.util.List;
import java.util.Objects;

public class AnswerServiceImpl extends CsvServiceAbstract<Answer> implements AnswerService {

    @SuppressWarnings("FieldCanBeLocal")
    private final String ANSWER_FILE = "answers.csv";

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        return findAllByFilter(getFilePath(ANSWER_FILE), Answer.class,
                (strategy) -> {
                    int columnIndex = strategy.getColumnIndex("QUESTION_ID");
                    return line -> Objects.equals(line[columnIndex], String.valueOf(questionId));
                });
    }
}
