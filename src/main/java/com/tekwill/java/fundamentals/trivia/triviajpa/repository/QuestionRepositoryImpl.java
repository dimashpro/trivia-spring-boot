package com.tekwill.java.fundamentals.trivia.triviajpa.repository;


import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findQuestionsByLevel(int level) {
        return entityManager.createQuery(
                "SELECT DISTINCT q FROM Question q JOIN FETCH q.answers WHERE q.level = :level", Question.class)
                .setParameter("level", level)
                .getResultList();
    }

    @Override
    public boolean save(Question question) {
        entityManager.persist(question);
        return true;
    }

    @Override
    public boolean delete(Question question) {
        entityManager.remove(question);
        return true;
    }

    @Override
    public List<Question> findAll() {
        return entityManager.createQuery("SELECT DISTINCT q FROM Question q JOIN FETCH q.answers",
                                         Question.class).getResultList();
    }

    @Override
    public Question findById(Long id) {
        return entityManager.createQuery("SELECT DISTINCT q FROM Question q JOIN FETCH q.answers WHERE q.id = :id",
                                         Question.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE FROM Question q WHERE q.id = :id").setParameter("id", id);
    }

}
