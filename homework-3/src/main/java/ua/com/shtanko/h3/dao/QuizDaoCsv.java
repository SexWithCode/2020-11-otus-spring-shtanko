package ua.com.shtanko.h3.dao;

import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.config.QuizProperties;
import ua.com.shtanko.h3.domain.Question;
import ua.com.shtanko.h3.util.content.ContentCsvParser;
import ua.com.shtanko.h3.util.content.LocalCsvLoader;

import java.io.IOException;
import java.util.List;

@Component
public class QuizDaoCsv implements QuizDao {

    private String source;
    private final ContentCsvParser contentCsvParser;
    private final LocalCsvLoader localCsvLoader;

    public QuizDaoCsv(ContentCsvParser contentCsvParser,
                      LocalCsvLoader localCsvLoader,
                      QuizProperties quizProperties) {
        this.contentCsvParser = contentCsvParser;
        this.localCsvLoader = localCsvLoader;
        this.source = quizProperties.getSource();
    }

    @Override
    public List<Question> getAllQuestions() throws IOException {
        return this.contentCsvParser.parse(localCsvLoader.getCsvContent(source));
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
