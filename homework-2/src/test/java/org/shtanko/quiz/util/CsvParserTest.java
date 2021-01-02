package org.shtanko.quiz.util;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import org.shtanko.quiz.domain.Question;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CsvParserTest {

    @Test
    public void shouldReturnQuestionsReadingValidResourcePath() throws IOException {
        CsvParser csvParser = new CsvParser();
        List<Question> questions = csvParser.parse("/quiz.csv");

        assertThat(questions, is(notNullValue()));
        assertThat(questions, hasSize(5));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionReadingInvalidResourcePath() throws IOException{
        new CsvParser().parse("/wrong-resource-location.csv");
    }
}
