package ua.com.shtanko.h3.config;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "quiz")
public class QuizProperties {
    private String source;
    private String localeLanguage;
    private String localeCountry;
    private Integer hit;

    public String getSource() {
        // Logic for defining used resource file name from default resource file name below
        return new StringBuilder()
                .append(FilenameUtils.removeExtension(source))
                .append("_")
                .append(localeLanguage)
                .append("_")
                .append(localeCountry)
                .append(".")
                .append(FilenameUtils.getExtension(source))
                .toString();
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleCountry() {
        return localeCountry;
    }

    public void setLocaleCountry(String localeCountry) {
        this.localeCountry = localeCountry;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }
}
