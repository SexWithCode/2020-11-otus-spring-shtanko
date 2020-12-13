package org.shtanko.quiz;

import org.shtanko.quiz.scenario.Scenario;
import org.shtanko.quiz.scenario.SimpleScenario;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="org.shtanko")
public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Scenario scenario = context.getBean(SimpleScenario.class);
        scenario.execute();
    }
}
