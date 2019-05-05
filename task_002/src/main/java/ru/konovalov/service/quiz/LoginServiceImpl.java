package ru.konovalov.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.konovalov.dto.Student;
import ru.konovalov.service.StudentService;
import ru.konovalov.service.util.ScannerService;

@Service
public class LoginServiceImpl implements LoginService {
    private final StudentService studentService;
    private final ScannerService scannerService;

    @Autowired
    public LoginServiceImpl(StudentService studentService, ScannerService scannerService) {
        this.studentService = studentService;
        this.scannerService = scannerService;
    }

    @Override
    public Student login() {
        System.out.println("Hello! Welcome to our quiz!");
        System.out.print("Please, enter your name <admin>: ");

        String name = "";
        String familyName = "";
        scannerService.initScanner();
        if (scannerService.hasNextLine()) {
            name = scannerService.nextLine();
        }

        System.out.print("Please, enter your family name <admin>: ");
        if (scannerService.hasNextLine()) {
            familyName = scannerService.nextLine();
        }

        return studentService.findByNameAndFamilyName(name, familyName);
    }
}

