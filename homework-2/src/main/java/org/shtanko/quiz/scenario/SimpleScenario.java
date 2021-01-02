package org.shtanko.quiz.scenario;

import org.shtanko.quiz.domain.Question;
import org.shtanko.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
@PropertySource("classpath:application.properties")
public class SimpleScenario implements Scenario{
    private final QuizService quizService;
    private final Integer passScore;

    public SimpleScenario(QuizService quizService, @Value("${pass.score}") Integer passScore){
        this.quizService = quizService;
        this.passScore = passScore;
    }

    @Override
    public void execute() throws IOException {
        final Scanner scanner = new Scanner(System.in);
        String name;
        String surname;
        Integer score = 0;

        //  Ask user to introduce himself/herself
        System.out.print("Please enter your name: ");
        name = scanner.nextLine();
        System.out.print("Please enter your surname: ");
        surname = scanner.nextLine();

        //  Greet the user
        System.out.printf("Hi, %s %s! %n", name, surname);
        System.out.println("Please answer the questions below.");

        //  Ask questions and score answers
        List<Question> questions = quizService.getAllQuestions();

        for (Question question : questions) {
            System.out.printf("Question is: %s %n", question.getQuestionMessage());
            System.out.printf("Answers are: %s %n", question.getAnswers().keySet().toString());

            if (question.isValidAnswer(scanner.nextLine())) {
                score++;
            }
        }

        //  Show user's and passing scores
        System.out.printf("Yor score is %d %n", score);
        System.out.printf("Passing score is %d %n", passScore);

        //  Make decision on passing the test
        System.out.printf("You have %s the test!", score >= passScore ? "passed" : "not passed");
    }
}
