package com.trivia.game.triviaspringboot.service;

import com.trivia.game.triviaspringboot.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByLevel(int level);
    boolean save(Question question);

    boolean delete(Question question);

    List<Question> getAll();
}
