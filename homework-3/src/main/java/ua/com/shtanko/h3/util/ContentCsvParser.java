package ua.com.shtanko.h3.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class ContentCsvParser {

    public List<Question> parse(String source) throws IOException {

        List<Question> questionsList = new ArrayList<>();

        InputStream inputStream = this.getClass().getResourceAsStream(source);

        if (isNull(inputStream)) {
            throw new IllegalArgumentException("Resource not found: " + source);
        }

        Reader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(source)));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        List<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            Question question = new Question();
            Map<String, String> answers = new HashMap<>();

            question.setQuestionMessage(csvRecord.get(0));

            //  Here we assume that the first answer in the list is the correct one
            answers.put(csvRecord.get(1), "true");
            answers.put(csvRecord.get(2), "false");
            answers.put(csvRecord.get(3), "false");
            question.setAnswers(answers);

            questionsList.add(question);
        }

        return questionsList;
    }
}
