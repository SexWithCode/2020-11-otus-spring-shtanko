package org.shtanko.quiz.service;

import org.shtanko.quiz.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuizService {
    List<Question> getAllQuestions() throws IOException;
}
