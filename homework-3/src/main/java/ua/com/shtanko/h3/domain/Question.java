package ua.com.shtanko.h3.domain;

import java.util.Map;

public class Question {
    private String questionMessage;
    private Map<String, String> answers;

    public Question() {
    }

    public Question(String questionMessage, Map<String, String> answers) {
        this.questionMessage = questionMessage;
        this.answers = answers;
    }

    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String questionMessage) {
        this.questionMessage = questionMessage;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public boolean isValidAnswer (String answer) {
        return Boolean.parseBoolean(this.answers.get(answer));
    }
}
