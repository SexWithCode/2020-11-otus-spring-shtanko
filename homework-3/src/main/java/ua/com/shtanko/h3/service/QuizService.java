package ua.com.shtanko.h3.service;

import ua.com.shtanko.h3.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuizService {
    List<Question> getAllQuestions() throws IOException;
}
