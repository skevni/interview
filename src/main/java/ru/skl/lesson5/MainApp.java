package ru.skl.lesson5;

import org.hibernate.SessionFactory;
import ru.skl.lesson5.dao.StudentRepository;
import ru.skl.lesson5.entities.Student;
import ru.skl.lesson5.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        StudentRepository studentRepository = new StudentRepository();

        Student student = Student.builder().name("Ivanov").mark(BigDecimal.valueOf(4.7)).build();
        studentRepository.add(student);

        student = studentRepository.findById(1L);
        student.setName("Sidorov");
        studentRepository.update(student);

        List<Student> students = Arrays.asList(Student.builder().name("Petrov").mark(BigDecimal.valueOf(4.4)).build(),
                Student.builder().name("Dovzhik").mark(BigDecimal.valueOf(3.0)).build(),
                Student.builder().name("Zelin").mark(BigDecimal.valueOf(5.0)).build());
        studentRepository.addAll(students);

//        studentRepository.deleteAll(studentRepository.findByName("Ivanov"));

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(Student.builder().name("Student" + (i + 1)).build());
        }

        studentRepository.addAll(list, 1000);

        for (Student student1 : studentRepository.findAll()) {
            System.out.println(student1.toString());
        }


        HibernateUtil.shutdownSessionFactory();
    }
}
