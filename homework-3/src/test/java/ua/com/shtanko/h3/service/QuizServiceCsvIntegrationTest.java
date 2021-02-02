package ua.com.shtanko.h3.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.shtanko.h3.dao.QuizDaoCsv;
import ua.com.shtanko.h3.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuizServiceCsvIntegrationTest {
    public static final String EXPECTED_QUESTION_IN_RUSSIAN = "Сколько у крысы лап?";
    public static final String EXPECTED_QUESTION_IN_ENGLISH = "How much legs does a rat has?";

    @Autowired
    QuizServiceCsv quizServiceCsv;

    @Autowired
    QuizDaoCsv quizDaoCsv;

    @Test
    public void shouldReadCorrectRussianQuestionWithUserRussianLocalization() throws IOException {
        quizDaoCsv.setSource("/quiz_ru_RU.csv");

        List<Question> questions = quizServiceCsv.getAllQuestions();
        assertThat(questions, is(notNullValue()));
        assertThat(questions.get(0).getQuestionMessage(), is(EXPECTED_QUESTION_IN_RUSSIAN));
    }

    @Test
    public void shouldReadCorrectEnglishQuestionWithUserEnglishLocalization() throws IOException {
        quizDaoCsv.setSource("/quiz_en_US.csv");

        List<Question> questions = quizServiceCsv.getAllQuestions();
        assertThat(questions, is(notNullValue()));
        assertThat(questions.get(0).getQuestionMessage(), is(EXPECTED_QUESTION_IN_ENGLISH));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionReadingInvalidResourcePath() throws IOException {
        quizDaoCsv.setSource("wrong_resource_location");

        quizServiceCsv.getAllQuestions();
    }

}