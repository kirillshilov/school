package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.StudentRepositori;

import java.util.*;


@Service
public class StudentService {
StudentRepositori studentRepositori;

    public StudentService(StudentRepositori studentRepositori) {
        this.studentRepositori = studentRepositori;
    }

    public Student addStudent(Student student) {
    studentRepositori.save(student);
    return student;

    }

    public Student findStudent(Long id) {
return studentRepositori.findById(id).get();
    }

    public Student changeStudent(Student student) {
    return studentRepositori.save(student);
    }

    public void deleteStudent(Long id) {
 studentRepositori.deleteById(id);
    }

    public Collection<Student> allStudentOfAge(Long age) {
   return studentRepositori.findByAge(age);
    }
    public Collection <Student> allStudentBetweenAge (Long minAge, Long maxAge){
        return studentRepositori.findByAgeBetween( minAge , maxAge);
    }
}

