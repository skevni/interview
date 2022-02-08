package ru.skl.lesson7.interview.services;

import org.springframework.stereotype.Service;
import ru.skl.lesson7.interview.entities.Student;
import ru.skl.lesson7.interview.repositories.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }
}
