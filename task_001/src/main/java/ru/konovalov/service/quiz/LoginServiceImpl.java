package ru.konovalov.service.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.konovalov.dto.Student;
import ru.konovalov.service.StudentService;

import java.util.Scanner;

public class LoginServiceImpl implements LoginService {
    @Override
    public Student login() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to our quiz!");
        System.out.print("Please, enter your name <admin>: ");

        String name = "";
        String familyName = "";
        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
        }

        System.out.print("Please, enter your family name <admin>: ");
        if (scanner.hasNextLine()) {
            familyName = scanner.nextLine();
        }

        StudentService studentService = context.getBean(StudentService.class);
        return studentService.findByNameAndFamilyName(name, familyName);
    }
}

