package ru.konovalov.service.quiz;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Answer;
import ru.konovalov.dto.Question;
import ru.konovalov.service.AnswerService;
import ru.konovalov.service.QuestionService;
import ru.konovalov.service.util.ScannerService;

import java.util.Collections;
import java.util.List;

@Service
public class QuizProcessServiceImpl implements QuizProcessService {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ScannerService scannerService;
    private static final String[] ANSWER_LABEL = new String[]{"A", "B", "C", "D", "E", "F"};
    @SuppressWarnings("FieldCanBeLocal")
    private static final int INCORRECT_LABEL_ATTEMPTS_COUNT = 3;

    public QuizProcessServiceImpl(QuestionService questionService, AnswerService answerService, ScannerService scannerService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.scannerService = scannerService;
    }

    @Override
    public int askQuestions() {
        int correctAnswers = 0;

        List<Question> questionList = getShuffledQuestions();

        int qNumber = 1;
        for (Question question : questionList) {
            System.out.printf("%nQuestion %d/%d:%n  %s%n", qNumber++, questionList.size(), question.getQuestion());
            List<Answer> answerList = getShuffledAnswers(question);

            if (answerList.size() > ANSWER_LABEL.length) {
                throw new IllegalArgumentException("Answers more than labels count. qId = " + question.getId());
            }

            for (int i = 0; i < answerList.size(); i++) {
                System.out.printf("%4s. %s%n", ANSWER_LABEL[i], answerList.get(i).getValue());
            }

            int obtainedLabelIndex = getEnteredAnswerLabelIndex();
            if (answerList.get(obtainedLabelIndex).getIsCorrect() == 1) {
                correctAnswers++;
            }


        }

        return correctAnswers;
    }

    private List<Question> getShuffledQuestions() {
        List<Question> questionList = questionService.findAll();
        Collections.shuffle(questionList);

        return questionList;
    }

    private List<Answer> getShuffledAnswers(@NonNull final Question question) {
        List<Answer> answerList = answerService.findByQuestionId(question.getId());
        Collections.shuffle(answerList);

        return answerList;
    }

    private int getEnteredAnswerLabelIndex() {
        return getEnteredAnswerLabelIndex(0);
    }

    private int getEnteredAnswerLabelIndex(int attemptNumber) {
        int answerIndex;
        scannerService.initScanner();
        System.out.print(" Your answer is : ");
        String usersAnswerLabel = scannerService.nextLine().trim();
        answerIndex = getEnteredAnswerLabel(usersAnswerLabel);
        if (answerIndex < 0) {
            if (attemptNumber >= INCORRECT_LABEL_ATTEMPTS_COUNT) {
                System.out.println("You are not serious enough. I'm out!");
//                throw new IllegalArgumentException("Can't work with stupid people!");
                System.exit(0);
            }
            System.out.println("Inserted variant does not exists! Please, enter correct label of answer.");
            return getEnteredAnswerLabelIndex(++attemptNumber);
        }

        return answerIndex;
    }

    private int getEnteredAnswerLabel(@NonNull final String usersAnswerLabel) {
        for (int i = 0; i < ANSWER_LABEL.length; i++) {
            if (ANSWER_LABEL[i].equalsIgnoreCase(usersAnswerLabel)) {
                return i;
            }
        }

        return -1;
    }
}
