package ua.com.shtanko.h3.util.content;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContentCsvParser {

    public List<Question> parse(List<CSVRecord> csvRecords) {

        List<Question> questionsList = new ArrayList<>();

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
