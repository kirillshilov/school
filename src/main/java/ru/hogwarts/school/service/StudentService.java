package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.FacultyRepository;
import ru.hogwarts.school.repositorie.StudentRepository;

import java.util.Collection;

import java.util.List;
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
        return studentRepository.save(student);
    }

    public Optional <Student> readStudent(Long id) {
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

    public Student deleteStudent(Long id) {

        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            studentRepository.findById(id).get().setFaculty(null);
         studentRepository.deleteById(id);
        return student;
        }
        return null;
    }

    public Collection <Student> allStudentOfAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> allStudentBetweenAge(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty  getStudentFaculty(Long id) {
       return  studentRepository.getReferenceById(id).getFaculty();


}

    public Long getCountOfStudent () {
        return studentRepository.getCountStudent();
    }

    public Double getAvgAge() {
        return studentRepository.getAvgAgeOfStudent();
    }

    public List<Student> getLastStudents() {
        return studentRepository.getLastStudents();
    }
}

