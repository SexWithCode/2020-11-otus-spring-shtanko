package org.shtanko.quiz.dao;

import org.shtanko.quiz.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuizDao {

    List<Question> getAllQuestions() throws IOException;
}
