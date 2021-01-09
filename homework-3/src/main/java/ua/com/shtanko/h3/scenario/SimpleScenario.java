package ua.com.shtanko.h3.scenario;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ua.com.shtanko.h3.config.QuizProperties;
import ua.com.shtanko.h3.domain.Question;
import ua.com.shtanko.h3.service.IOService;
import ua.com.shtanko.h3.service.QuizService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Component
public class SimpleScenario implements Scenario{
    private final QuizService quizService;
    private final QuizProperties quizProperties;
    private final MessageSource messageSource;
    private final IOService ioService;

    public SimpleScenario(QuizService quizService,
                          QuizProperties quizProperties,
                          MessageSource messageSource,
                          IOService ioService){
        this.quizService = quizService;
        this.quizProperties = quizProperties;
        this.messageSource = messageSource;
        this.ioService = ioService;
    }

    @Override
    public void execute() throws IOException {
        int score = 0;

        //  Set quiz's locale from configuration file
        Locale locale = new Locale(quizProperties.getLocaleLanguage(), quizProperties.getLocaleCountry());

        //  Ask user to introduce himself/herself
        ioService.displayMessage(messageSource.getMessage("name.request", null, locale));
        String name = ioService.readMessage();

        ioService.displayMessage(messageSource.getMessage("surname.request", null, locale));
        String surname = ioService.readMessage();

        //  Greet the user
        ioService.displayMessage(messageSource.getMessage("greetings.message", new String[]{name, surname}, locale));

        //  Ask to answer the questions
        ioService.displayMessage(messageSource.getMessage("answers.provide.message", null, locale));

        //  Ask questions and score answers
        List<Question> questions = quizService.getAllQuestions();

        for (Question question : questions) {
            ioService.displayMessage(messageSource.getMessage("questions.message", new String[]{question.getQuestionMessage()}, locale));
            ioService.displayMessage(messageSource.getMessage("answers.message", new String[]{question.getAnswers().keySet().toString()}, locale));

            if (question.isValidAnswer(ioService.readMessage())) {
                score++;
            }
        }

        //  Show user's and passing scores
        ioService.displayMessage(messageSource.getMessage("your.score.message", new String[]{String.valueOf(score)}, locale));
        ioService.displayMessage(messageSource.getMessage("passing.score.message", new String[]{quizProperties.getHit().toString()}, locale));

        //  Make decision on passing the test
        if (score >= quizProperties.getHit()) {
            ioService.displayMessage(messageSource.getMessage("positive.decision.message", null, locale));
        } else {
            ioService.displayMessage(messageSource.getMessage("negative.decision.message", null, locale));
        }
    }
}
