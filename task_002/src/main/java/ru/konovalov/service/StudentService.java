package ru.konovalov.service;

import ru.konovalov.dto.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findByNameAndFamilyName(String name, String familyName);
}
