package ru.skl.lesson5.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private BigDecimal mark;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private String name;
        private BigDecimal mark;

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder mark(BigDecimal mark) {
            this.mark = mark;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.setName(name);
            student.setMark(mark);
            return student;
        }
    }

}
