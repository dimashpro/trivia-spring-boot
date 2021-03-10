package com.tekwill.java.fundamentals.trivia.triviajpa.service;

import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Question;
import com.tekwill.java.fundamentals.trivia.triviajpa.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public List<Question> getQuestionsByLevel(int level) {
        List<Question> questionsByLevel = questionRepository.findQuestionsByLevel(level);
        Collections.shuffle(questionsByLevel);
        return questionsByLevel;
    }

    @Override
    @Transactional
    public boolean save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public boolean delete(Question question) {
        return questionRepository.delete(question);
    }

    @Override
    @Transactional
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question getById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
