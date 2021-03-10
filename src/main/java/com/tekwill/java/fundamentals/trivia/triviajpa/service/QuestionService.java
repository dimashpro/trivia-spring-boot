package com.tekwill.java.fundamentals.trivia.triviajpa.service;

import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Question;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByLevel(int level);

    boolean save(Question question);

    boolean delete(Question question);

    List<Question> getAll();

    Question getById(Long id);

    void deleteById(Long id);
}
