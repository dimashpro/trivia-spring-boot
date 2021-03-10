package com.tekwill.java.fundamentals.trivia.triviajpa.domain;


import com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions.EmptyAnswerTextException;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions.EmptyQuestionTextException;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions.InvalidLevelException;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions.InvalidScoreException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private int score;
    private int level;
    private String text;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    public Question(int score, int level, String text, Set<Answer> answers) {
        this(score, level, text);
        this.answers = answers;
        this.answers.forEach(a -> a.setQuestion(this));
    }

    public Question(int score, int level, String text) {
        if(text.isEmpty()){
            throw new EmptyQuestionTextException("Question text should not be empty");
        }
        if(score < 0){
            throw  new InvalidScoreException("Score must be greater than 0");
        }
        if(level < 0){
            throw  new InvalidLevelException("Level must be greater than 0");
        }
        this.score = score;
        this.level = level;
        this.text = text;
    }

    public Answer getCorrectAnswer() {
        for (Answer answer : answers) {
            if (answer.isCorrect())
                return answer;
        }
        return null;
    }

    public List<Answer> getWrongAnswers() {
        List<Answer> wrongAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            if (!answer.isCorrect()) {
                wrongAnswers.add(answer);
            }
        }
        Collections.shuffle(wrongAnswers);
        return wrongAnswers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(null);
    }
}
