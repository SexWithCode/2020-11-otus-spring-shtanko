package ua.com.shtanko.h3.dao;

import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.config.QuizProperties;
import ua.com.shtanko.h3.domain.Question;
import ua.com.shtanko.h3.util.ContentCsvParser;

import java.io.IOException;
import java.util.List;

@Component
public class QuizDaoCsvImpl implements QuizDao {

    private String source;
    private final ContentCsvParser contentCsvParser;

    public QuizDaoCsvImpl(ContentCsvParser contentCsvParser, QuizProperties quizProperties) {
        this.contentCsvParser = contentCsvParser;
        this.source = quizProperties.getSource();
    }

    @Override
    public List<Question> getAllQuestions() throws IOException {
        return this.contentCsvParser.parse(source);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
