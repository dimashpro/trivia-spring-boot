package com.learning.game.repository;

import com.learning.game.domain.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findQuestionsByLevel(int level);

    boolean save(Question question);

    boolean delete(Question question);

    List<Question> findAll();
}
