package org.shtanko.quiz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.shtanko.quiz.dao.QuizDaoCsv;
import org.shtanko.quiz.domain.Question;
import org.shtanko.quiz.util.CsvParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class QuizServiceCsvTest {

    @Mock
    QuizDaoCsv quizDaoCsv;

    @Mock
    CsvParser csvParser;

    @InjectMocks
    QuizServiceCsv quizServiceCsv;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllQuestionsTest() {
        List<Question> s = new ArrayList<>();
        Question sourceQuestion = new Question("Is this a test message?",
                new HashMap<>() {{
                    put("Correct", "true");
                    put("Wrong", "false");
                }});

        s.add(sourceQuestion);

//        when(quizServiceCsv.getAllQuestions()).thenReturn(sourceQuestion);
//
//        Question returnedQuestion = s.get(0);
//        assertEquals("Is this a test message?", returnedQuestion.getQuestionMessage());

    }
}
