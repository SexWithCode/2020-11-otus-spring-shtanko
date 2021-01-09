package ua.com.shtanko.h3.dao;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h3.config.QuizProperties;
import ua.com.shtanko.h3.domain.Question;
import ua.com.shtanko.h3.util.CsvParser;

import java.io.IOException;
import java.util.List;

@Repository
public class QuizDaoCsvImpl implements QuizDao {

    private String source;
    private final CsvParser csvParser;

    public QuizDaoCsvImpl(CsvParser csvParser, QuizProperties quizProperties) {
        this.csvParser = csvParser;
        this.source = quizProperties.getSource();
    }

    @Override
    public List<Question> getAllQuestions() throws IOException {
        return this.csvParser.parse(source);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
