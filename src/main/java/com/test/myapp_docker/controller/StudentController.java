package com.test.myapp_docker.controller;

import com.test.myapp_docker.entities.Student;
import com.test.myapp_docker.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(("/students"))
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping
    public List<String> getStudents() {
        return studentRepository.findAll().stream().map(Student::toString).collect(Collectors.toList());
    }

    @GetMapping("/test-data")
    public List<String > getTestData(){
        return List.of("FirstName LastName1", "FirstName LastName2");
    }


}
