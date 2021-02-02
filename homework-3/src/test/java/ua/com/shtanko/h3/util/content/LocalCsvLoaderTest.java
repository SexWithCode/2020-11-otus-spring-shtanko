package ua.com.shtanko.h3.util.content;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalCsvLoaderTest {
    public static final String WRONG_RESOURCE_LOCATION = "null";
    public static final String CORRECT_RESOURCE_LOCATION = "/quiz.csv";
    public static final Integer EXPECTED_RECORDS_COUNT = 5;

    @Autowired
    LocalCsvLoader localCsvLoader;

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionWhenReadingWrongResourceLocation() throws IOException {
        localCsvLoader.getCsvContent(WRONG_RESOURCE_LOCATION);
    }

    @Test
    public void shouldReturnExpectedRecordsCountWhenReadingCorrectResourceLocation() throws IOException {
        List<CSVRecord> csvRecords = localCsvLoader.getCsvContent(CORRECT_RESOURCE_LOCATION);
        assertThat(csvRecords, is(notNullValue()));
        assertThat(csvRecords, hasSize(EXPECTED_RECORDS_COUNT));
    }
}
