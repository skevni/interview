package ru.skl.lesson7.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skl.lesson7.interview.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
