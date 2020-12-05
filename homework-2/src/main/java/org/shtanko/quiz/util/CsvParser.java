package org.shtanko.quiz.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.shtanko.quiz.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

    public List<Question> parse(String source) {

        List<Question> questionsList = new ArrayList<>();

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionsList;
    }
}
