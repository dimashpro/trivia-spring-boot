package com.learning.game.service;

import com.learning.game.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByLevel(int level);
    boolean save(Question question);

    boolean delete(Question question);

    List<Question> getAll();
}
