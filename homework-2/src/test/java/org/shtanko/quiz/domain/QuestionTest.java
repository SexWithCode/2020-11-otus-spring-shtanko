package org.shtanko.quiz.domain;

import org.junit.Test;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuestionTest {

    @Test
    public void shouldReturnValidAnswerSelection() {
        Question question = prepareQuestion();

        assertThat(question.isValidAnswer("correct"), is(true));
    }

    private Question prepareQuestion() {
        return new Question("Is this a test message?",
                new HashMap<>() {{
                    put("correct", "true");
                    put("wrong", "false");
                }}
        );
    }
}
