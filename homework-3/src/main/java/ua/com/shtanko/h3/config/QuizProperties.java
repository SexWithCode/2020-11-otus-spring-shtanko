package ua.com.shtanko.h3.config;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "quiz")
public class QuizProperties  {
    private String source;
    private Integer hit;

    public String getSource() {
        // Logic for defining resource file name below
        return FilenameUtils.removeExtension(source) + "_" + Locale.getDefault() + ".csv";
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }
}
