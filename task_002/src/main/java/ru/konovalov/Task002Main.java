package ru.konovalov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ru.konovalov.config.AppConfig;
import ru.konovalov.service.quiz.QuizRunService;

@Component
@ComponentScan("ru.konovalov.service")
@Import(AppConfig.class)
public class Task002Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Task002Main.class);

        QuizRunService quizRunService = context.getBean(QuizRunService.class);
        quizRunService.run();
    }

}
