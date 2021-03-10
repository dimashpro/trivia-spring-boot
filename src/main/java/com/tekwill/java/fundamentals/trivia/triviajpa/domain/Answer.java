package com.tekwill.java.fundamentals.trivia.triviajpa.domain;


import com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions.EmptyAnswerTextException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ANSWER")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private boolean isCorrect;
    private String letter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer(String text, boolean isCorrect, String letter) {
        if(text.isEmpty()){
            throw new EmptyAnswerTextException("Answer text should not be empty");
        }
        this.text = text;
        this.isCorrect = isCorrect;
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        return id != null && id.equals(((Answer) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return letter + ". " + text;
    }
}
