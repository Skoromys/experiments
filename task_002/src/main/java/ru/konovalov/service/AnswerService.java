package ru.konovalov.service;

import ru.konovalov.dto.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> findByQuestionId(int questionId);
}
