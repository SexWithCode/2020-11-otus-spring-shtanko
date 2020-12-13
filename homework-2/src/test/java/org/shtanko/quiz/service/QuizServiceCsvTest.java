package org.shtanko.quiz.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.shtanko.quiz.dao.QuizDaoCsvImpl;
import org.shtanko.quiz.domain.Question;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

public class QuizServiceCsvTest {
    QuizService quizServiceCsv;
    QuizDaoCsvImpl quizDaoCsv;

    @Before
    public void beforeClass() {
        quizDaoCsv = Mockito.mock(QuizDaoCsvImpl.class);
        quizServiceCsv = new QuizServiceCsv(quizDaoCsv);
    }

    @Test
    public void shouldReturnValidQuestions() throws IOException {
        when(quizDaoCsv.getAllQuestions()).thenReturn(prepareQuestions());

        List<Question> questions = quizServiceCsv.getAllQuestions();

        assertThat(questions, is(notNullValue()));
        assertThat(questions, hasSize(1));
        assertThat(questions.get(0).getQuestionMessage(), is("Is this a test message?"));
    }

    private List<Question> prepareQuestions() {
        List<Question> questions = new ArrayList<>();
        Question sourceQuestion = new Question("Is this a test message?",
                new HashMap<>() {{
                    put("correct", "true");
                    put("wrong", "false");
                }}
        );

        questions.add(sourceQuestion);

        return questions;
    }
}
