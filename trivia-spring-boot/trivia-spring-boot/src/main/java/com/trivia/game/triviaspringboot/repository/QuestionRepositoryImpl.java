package com.trivia.game.triviaspringboot.repository;

import com.trivia.game.triviaspringboot.domain.Answer;
import com.trivia.game.triviaspringboot.domain.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String database = "trivia-db";
    private final String userName = "postgres";
    private final String password = "123456";

    @Override
    public List<Question> findQuestionsByLevel(int level) {
        List<Question> questions = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(url + database, userName, password);
             PreparedStatement ps = c.prepareStatement("SELECT * FROM QUESTION Q  WHERE Q.LEVEL = ?");
             PreparedStatement ps2 = c.prepareStatement("SELECT * FROM ANSWER A  WHERE A.QUESTION_ID = ?")) {

            ps.setInt(1, level);

            //retrieve question
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    Question question = new Question(r.getLong("id"), r.getInt("score"),
                            r.getInt("level"), r.getString("text"));
                    questions.add(question);
                }
            }

            //retrieve answers
            for (Question q : questions) {
                ps2.setLong(1, q.getId());
                try (ResultSet r = ps2.executeQuery()) {
                    while (r.next()) {
                        Answer answer = new Answer(r.getLong("id"), r.getString("text"),
                                r.getBoolean("is_correct"), r.getString("letter"));
                        q.addAnswer(answer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public boolean save(Question question) {
        try (Connection c = DriverManager.getConnection(url + database, userName, password);
             PreparedStatement ps = c.prepareStatement("INSERT INTO QUESTION(level, score, text) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ps2 = c.prepareStatement(
                     "INSERT INTO ANSWER(is_correct, letter, text, question_id) VALUES (?, ?, ?, ?)")) {
            //insert question

            ps.setInt(1, question.getLevel());
            ps.setInt(2, question.getScore());
            ps.setString(3, question.getText());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next())
                    question.setId(generatedKeys.getLong(1));
            }
            //insert answers
            for (Answer a : question.getAnswers()) {
                ps2.setBoolean(1, a.isCorrect());
                ps2.setString(2, a.getLetter());
                ps2.setString(3, a.getText());
                ps2.setLong(4, question.getId());
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Question question) {
        try (Connection c = DriverManager.getConnection(url + database, userName, password);
             PreparedStatement ps = c.prepareStatement("DELETE FROM ANSWER WHERE QUESTION_ID = ?");
             PreparedStatement ps2 = c.prepareStatement("DELETE FROM QUESTION WHERE ID = ?")) {

            //delete answers
            ps.setLong(1, question.getId());
            ps.executeUpdate();

            //delete question
            ps2.setLong(1, question.getId());
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(url + database, userName, password);
             PreparedStatement ps = c.prepareStatement("SELECT * FROM QUESTION Q");
             PreparedStatement ps2 = c.prepareStatement("SELECT * FROM ANSWER A  WHERE A.QUESTION_ID = ?")) {
            //retrieve question

            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    Question question = new Question(r.getLong("id"), r.getInt("score"),
                            r.getInt("level"), r.getString("text"));
                    questions.add(question);
                }
            }

            //retrieve answers
            for (Question q : questions) {
                ps2.setLong(1, q.getId());
                try (ResultSet r = ps2.executeQuery()) {
                    while (r.next()) {
                        Answer answer = new Answer(r.getLong("id"), r.getString("text"),
                                r.getBoolean("is_correct"), r.getString("letter"));
                        q.addAnswer(answer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
