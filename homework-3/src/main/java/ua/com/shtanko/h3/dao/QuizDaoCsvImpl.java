package ua.com.shtanko.h3.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ua.com.shtanko.h3.domain.Question;
import ua.com.shtanko.h3.util.CsvParser;

import java.io.IOException;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class QuizDaoCsvImpl implements QuizDao {

    private final String source;
    private final CsvParser csvParser;

    public QuizDaoCsvImpl(CsvParser csvParser, @Value("${source}") String source) {
        this.csvParser = csvParser;
        this.source = source;
    }

    @Override
    public List<Question> getAllQuestions() throws IOException {
        return this.csvParser.parse(source);
    }
}
