package ru.konovalov;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.konovalov.dto.Student;
import ru.konovalov.service.quiz.LoginService;
import ru.konovalov.service.quiz.QuizProcessService;

public class Task001Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        LoginService loginService = context.getBean("loginService", LoginService.class);
        QuizProcessService quizProcessService = context.getBean("quizProcessService", QuizProcessService.class);

        Student student = loginService.login();
        if (student == null) {
            System.out.println("You are not our student, go away!!!");
            System.exit(0);
        }
        int correctAnswers = quizProcessService.askQuestions();

        System.out.printf("%nCorrect answers for student %s %s is %d %n", student.getFamilyName(), student.getName(), correctAnswers);
    }

}
