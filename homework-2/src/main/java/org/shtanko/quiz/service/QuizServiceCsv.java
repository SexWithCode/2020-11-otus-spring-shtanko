package org.shtanko.quiz.service;

import org.shtanko.quiz.dao.QuizDao;
import org.shtanko.quiz.domain.Question;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QuizServiceCsv implements QuizService {
    private final QuizDao quizDao;

    public QuizServiceCsv(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public List<Question> getAllQuestions() throws IOException {
        return this.quizDao.getAllQuestions();
    }
}
