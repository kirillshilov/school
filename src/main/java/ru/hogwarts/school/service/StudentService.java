package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import ru.hogwarts.school.Exceptions.FacultyNotFoundException;

import ru.hogwarts.school.Exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.FacultyRepository;
import ru.hogwarts.school.repositorie.StudentRepository;

import java.util.Collection;
import java.util.Optional;


@Service
public class StudentService {
    StudentRepository studentRepository;

    FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;

        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        Faculty faculty = facultyRepository.findById(student.getFaculty().getId()).orElseThrow();
        student.setFaculty(faculty);
        studentRepository.save(student);
        return student;
    }

    public Optional<Student> readStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Student putStudent(Student student) {
        Faculty faculty = facultyRepository.getReferenceById(student.getFaculty().getId());
        if (studentRepository.findById(student.getId()).isPresent()) {
            student.setFaculty(faculty);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        }
        throw new StudentNotFoundException();
    }

    public Collection<Student> allStudentOfAge(Long age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> allStudentBetweenAge(Long minAge, Long maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty getStudentFaculty(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (student.getFaculty() == null) {
            throw new FacultyNotFoundException();
        }
        return null;
    }


}

