package ua.com.shtanko.h3.dao;

import ua.com.shtanko.h3.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuizDao {

    List<Question> getAllQuestions() throws IOException;
}
