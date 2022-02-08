package ru.skl.lesson7.interview.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.skl.lesson7.interview.entities.Student;
import ru.skl.lesson7.interview.services.StudentService;

import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.findById(id).orElseThrow(()->new RuntimeException("Student id:" + id + " not found"));
    }

    @GetMapping
    public List<Student> getStudents(@PathVariable long id) {
        return studentService.findAll();
    }

    @PutMapping
    public Student updateStudent(@RequestParam Student student) {
        return studentService.save(student);
    }

    @PostMapping
    public Student addStudent(@RequestParam Student student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteStudents() {
        studentService.deleteAll();
    }
}
