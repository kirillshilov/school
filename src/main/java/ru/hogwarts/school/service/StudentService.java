package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.StudentRepository;

import java.util.*;


@Service
public class StudentService {
StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
    studentRepository.save(student);
    return student;

    }

    public Student findStudent(Long id) {
return studentRepository.findById(id).get();
    }

    public Student changeStudent(Student student) {
    return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
 studentRepository.deleteById(id);
    }

    public Collection<Student> allStudentOfAge(Long age) {
   return studentRepository.findByAge(age);
    }
    public Collection <Student> allStudentBetweenAge (Long minAge, Long maxAge){
        return studentRepository.findByAgeBetween( minAge , maxAge);
    }
}

