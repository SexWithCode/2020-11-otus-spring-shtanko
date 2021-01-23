package ua.com.shtanko.h3.util.content;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class LocalCsvLoader {

    public List<CSVRecord> getCsvContent(String source) throws IOException {

        InputStream inputStream = this.getClass().getResourceAsStream(source);

        if (isNull(inputStream)) {
            throw new IllegalArgumentException("Resource not found: " + source);
        }

        var reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(source)));

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        return csvParser.getRecords();
    }
}
