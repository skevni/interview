package ru.skl.lesson5.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.core.NativeQuery;
import ru.skl.lesson5.entities.Student;
import ru.skl.lesson5.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {
    public static final int BUFFER_SIZE_FLUSH = 100;

    public StudentRepository() {
    }

    public Student findById(long id) {
        Student student;
        try (Session session = HibernateUtil.openSession()) {
            student = session.get(Student.class, id);
        }
        return student;
    }

    public List<Student> findByName(String name) {
        List<Student> students;
        try (Session session = HibernateUtil.openSession()) {
            students = session.createNativeQuery("select * from student where name = :criteria", Student.class).setParameter("criteria",name).list();
        }
        return students;
    }

    public void add(Student student) {
        addAll(Collections.singletonList(student), 1);
    }

    public void addAll(List<Student> students, int bufferSizeFlush) {
        if (students != null && !students.isEmpty()) {
            try (Session session = HibernateUtil.openSession()) {
                Transaction tr = session.beginTransaction();
                try {
                    int count = 0;
                    for (Student student : students) {
                        session.save(student);
                        if (count++ % bufferSizeFlush == 0) {
                            flushClear(session);
                        }
                    }
                    flushClear(session);
                    tr.commit();
                } catch (Exception e) {
                    tr.rollback();
                }
            }
        }
    }

    public void addAll(List<Student> students) {
        addAll(students, BUFFER_SIZE_FLUSH);
    }

    public void delete(Student student) {
        deleteAll(Collections.singletonList(student), 1);
    }

    public void deleteAll(List<Student> students, int bufferSizeFlush) {
        if (students != null && !students.isEmpty()) {
            try (Session session = HibernateUtil.openSession()) {
                Transaction tr = session.beginTransaction();
                try {
                    int count = 0;
                    for (Student student : students) {
                        session.delete(student);
                        if (count++ % bufferSizeFlush == 0) {
                            flushClear(session);
                        }
                    }
                    flushClear(session);
                    tr.commit();
                } catch (Exception e) {
                    tr.rollback();
                }
            }
        }
    }

    public void deleteAll(List<Student> students) {
        deleteAll(students, BUFFER_SIZE_FLUSH);
    }

    public void update(Student student) {
        updateAll(Collections.singletonList(student), 1);
    }

    public void updateAll(List<Student> students, int bufferSizeFlush) {
        if (students != null && !students.isEmpty()) {
            try (Session session = HibernateUtil.openSession()) {
                Transaction tr = session.beginTransaction();
                try {
                    int count = 0;
                    for (Student student : students) {
                        session.update(student);
                        if (count++ % bufferSizeFlush == 0) {
                            flushClear(session);
                        }
                    }

                    flushClear(session);
                    tr.commit();
                } catch (Exception e) {
                    tr.rollback();
                }
            }
        }
    }


    private void flushClear(Session session) {
        session.flush();
        session.clear();
    }
}
