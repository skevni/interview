package ru.skl.lesson7.interview.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skl.lesson7.interview.entities.Student;
import ru.skl.lesson7.interview.services.StudentService;

@Controller
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.findById(id).orElseThrow(()->new RuntimeException("Student id:" + id + " not found"));
    }
}
