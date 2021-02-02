package ua.com.shtanko.h3.util.content;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.shtanko.h3.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContentCsvParserTest {
    final static Integer EXPECTED_QUESTIONS_COUNT = 5;
    final static String EXPECTED_QUESTION_MESSAGE = "How much legs does a rat has?";
    final static String CORRECT_ANSWER = "4";
    final static String WRONG_ANSWER = "2";
    final static String DATA = new StringBuilder()
            .append("How much legs does a rat has?,4,6,2")
            .append("\n")
            .append("What's colour is rat's nose?,rose,black,white")
            .append("\n")
            .append("Does a rat has a fur?,yes,no,unsure")
            .append("\n")
            .append("What's an average rat's tail length in centimetres?,6,7,8")
            .append("\n")
            .append("Do you like rats?,yes,no,unsure")
            .toString();

    @Autowired
    ContentCsvParser contentCsvParser;

    @Test
    public void shouldReturnExpectedQuestionsList() throws IOException {
        List<Question> questions = contentCsvParser.parse(CSVParser.parse(DATA, CSVFormat.DEFAULT).getRecords());

        assertThat(questions, is(notNullValue()));
        assertThat(questions, hasSize(EXPECTED_QUESTIONS_COUNT));
        assertThat(questions.get(0).getQuestionMessage(), is(EXPECTED_QUESTION_MESSAGE));
        assertThat(questions.get(0).isValidAnswer(CORRECT_ANSWER), is(Boolean.TRUE));
        assertThat(questions.get(0).isValidAnswer(WRONG_ANSWER), is(Boolean.FALSE));
    }
}
