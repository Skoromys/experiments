package ru.konovalov.service.quiz;


import org.junit.Test;
import org.springframework.context.MessageSource;
import ru.konovalov.dto.Student;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class QuizRunServiceImplTest {

    @Test
    public void run() {
        QuizProcessService quizProcessServiceMock = quizProcessService();
        LoginService loginServiceMock = loginService();
        MessageSource messageSource = mock(MessageSource.class);

        QuizRunServiceImpl quizRunService = new QuizRunServiceImpl(quizProcessServiceMock, loginServiceMock, messageSource);
        quizRunService.run();

        verify(loginServiceMock, times(1)).login();
        verify(quizProcessServiceMock, times(1)).askQuestions();
    }


    private static QuizProcessService quizProcessService() {
        QuizProcessService mock = mock(QuizProcessServiceImpl.class);
        given(mock.askQuestions()).willReturn(0);
        return mock;
    }

    private static LoginService loginService() {
        LoginService mock = mock(LoginServiceImpl.class);
        given(mock.login()).willReturn(new Student()
                .setName("testName")
                .setFamilyName("testFamilyName")
                .setId(-1));
        return mock;
    }

}