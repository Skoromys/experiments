package ru.konovalov.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Student;

import java.util.Locale;

@Service
public class QuizRunServiceImpl implements QuizRunService {

    private final QuizProcessService quizProcessService;
    private final LoginService loginService;
    private final MessageSource messageSource;

    @Autowired
    public QuizRunServiceImpl(QuizProcessService quizProcessService,
                              LoginService loginService,
                              MessageSource messageSource) {
        this.quizProcessService = quizProcessService;
        this.loginService = loginService;
        this.messageSource = messageSource;
    }

    @Override
    public void run() {

        Student student = loginService.login();
        if (student == null) {
            System.out.println(messageSource.getMessage("quiz_run.login_fail", new String[]{}, Locale.ENGLISH));
            System.exit(0);
        }
        int correctAnswers = quizProcessService.askQuestions();

        System.out.println(messageSource.getMessage("quiz_run.print_result",
                new String[]{student.getFamilyName(), student.getName(), String.valueOf(correctAnswers)},
                Locale.ENGLISH));

    }
}
