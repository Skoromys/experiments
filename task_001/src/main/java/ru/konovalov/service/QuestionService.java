package ru.konovalov.service;

import ru.konovalov.dto.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();
}
