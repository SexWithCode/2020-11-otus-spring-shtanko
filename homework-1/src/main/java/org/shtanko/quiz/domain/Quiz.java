package org.shtanko.quiz.domain;

import java.util.Set;

public class Quiz {
    Set<Question> questions;

    public Quiz() {
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
