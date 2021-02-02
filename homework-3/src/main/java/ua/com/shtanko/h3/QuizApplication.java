package ua.com.shtanko.h3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ua.com.shtanko.h3.config.QuizProperties;
import ua.com.shtanko.h3.scenario.Scenario;
import ua.com.shtanko.h3.scenario.SimpleScenario;

@SpringBootApplication
@EnableConfigurationProperties(QuizProperties.class)
public class QuizApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(QuizApplication.class, args);

		Scenario scenario = context.getBean(SimpleScenario.class);
		scenario.execute();
	}
}
