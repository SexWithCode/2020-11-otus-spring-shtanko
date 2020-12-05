package org.shtanko.quiz.service;

import org.shtanko.quiz.dao.QuizDao;
import org.shtanko.quiz.dao.QuizDaoCsv;
import org.shtanko.quiz.domain.Question;
import org.shtanko.quiz.util.CsvParser;

import java.util.List;

public class QuizServiceCsv implements QuizService {
    private final QuizDao quizDaoCsv;
    private final CsvParser csvParser;

    public QuizServiceCsv(QuizDaoCsv quizDaoCsv, CsvParser csvParser) {
        this.quizDaoCsv = quizDaoCsv;
        this.csvParser = csvParser;
    }

    public QuizDao getQuizDaoCsv() {
        return quizDaoCsv;
    }

    public CsvParser getCsvParser() {
        return csvParser;
    }

    public List<Question> getAllQuestions() {
        return this.csvParser.parse(this.quizDaoCsv.getSource());
    }
}
