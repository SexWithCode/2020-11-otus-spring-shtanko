package ua.com.shtanko.h3.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.domain.Question;

import java.io.*;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class CsvParser {

    public List<Question> parse(String source) throws IOException{

            InputStream inputStream = this.getClass().getResourceAsStream(source);

            if (isNull(inputStream)) {
                throw new IllegalArgumentException("Resource not found: " + source);
            }

            Reader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(source)));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        return QuestionsComposer.compose(csvParser.getRecords());
    }
}
