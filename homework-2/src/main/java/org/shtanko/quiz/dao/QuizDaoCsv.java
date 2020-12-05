package org.shtanko.quiz.dao;

public class QuizDaoCsv implements QuizDao {
    final String source;

    public QuizDaoCsv(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
