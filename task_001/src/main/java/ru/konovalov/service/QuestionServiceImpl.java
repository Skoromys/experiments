package ru.konovalov.service;

import ru.konovalov.dto.Question;

import java.util.List;

public class QuestionServiceImpl extends CsvServiceAbstract<Question> implements QuestionService {

    @SuppressWarnings("FieldCanBeLocal")
    private final String QUESTION_FILE = "questions.csv";

    @Override
    public List<Question> findAll() {
        return findAll(getFilePath(QUESTION_FILE), Question.class);
    }
}
