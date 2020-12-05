package org.shtanko.quiz;

import org.shtanko.quiz.domain.Question;
import org.shtanko.quiz.service.QuizServiceCsv;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizServiceCsv quizServiceCsv = context.getBean(QuizServiceCsv.class);

        final Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        scanner.nextLine();

        System.out.print("Please enter your surname: ");
        scanner.nextLine();

        final List<Question> questions = quizServiceCsv.getAllQuestions();

        for (Question question : questions) {
            System.out.printf("Question is: %s %n", question.getQuestionMessage());
            System.out.printf("Answers are: %s %n", question.getAnswers().keySet().toString());
        }
    }
}
