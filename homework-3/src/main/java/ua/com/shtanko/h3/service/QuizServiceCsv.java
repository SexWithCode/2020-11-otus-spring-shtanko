package ua.com.shtanko.h3.service;

import org.springframework.stereotype.Service;
import ua.com.shtanko.h3.dao.QuizDao;
import ua.com.shtanko.h3.domain.Question;

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
