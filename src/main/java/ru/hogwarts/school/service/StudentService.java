package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.FacultyRepository;
import ru.hogwarts.school.repositorie.StudentRepository;

import java.util.Collection;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Service
public class StudentService {
    StudentRepository studentRepository;

    FacultyRepository facultyRepository;


    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;

        this.facultyRepository = facultyRepository;

    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.debug("method createStudent is started");
        Faculty faculty = facultyRepository.findById(student.getFaculty().getId()).orElseThrow();
        student.setFaculty(faculty);
        return studentRepository.save(student);
    }

    public Optional<Student> readStudent(Long id) {
        logger.debug("method readStudent is started");
        if (studentRepository.findById(id).isEmpty()) {
            logger.error("student not found");
        }
        return studentRepository.findById(id);
    }

    public Student putStudent(Student student) {
        logger.debug("method putStudent is started");
        Faculty faculty = facultyRepository.getReferenceById(student.getFaculty().getId());
        if (studentRepository.findById(student.getId()).isPresent()) {
            student.setFaculty(faculty);
            logger.warn("student changed");
            return studentRepository.save(student);
        }
        logger.error("student not found");
        return null;
    }

    public Student deleteStudent(Long id) {

        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            studentRepository.findById(id).get().setFaculty(null);
            studentRepository.deleteById(id);
            logger.warn("student deleted");
            return student;
        }
        logger.error("student not found");
        return null;
    }

    public Collection<Student> allStudentOfAge(int age) {
        logger.debug("method allStudentOfAge is started");
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> allStudentBetweenAge(int minAge, int maxAge) {
        logger.debug("method allStudentBetweenAge is started");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty getStudentFaculty(Long id) {
        return studentRepository.getReferenceById(id).getFaculty();


    }

    public Long getCountOfStudent() {
        logger.debug("method getCountOfStudent is started");
        return studentRepository.getCountStudent();
    }

    public Double getAvgAge() {
        logger.debug("method getAvgAge is started");
        return studentRepository.getAvgAgeOfStudent();
    }

    public List<Student> getLastStudents() {
        logger.debug("method getLastStudents is started");
        return studentRepository.getLastStudents();
    }

    public List<String> getStartWithAStudent() {
        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public OptionalDouble getAvgAgeOfStudent() {
        OptionalDouble temp = studentRepository.findAll()
                .stream()
                .parallel()
                .mapToInt(Student::getAge)
                .average();

        return temp;


    }

    public void getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList.get(0));
        System.out.println(studentList.get(1));

        new Thread(() ->
        {
            System.out.println(studentList.get(2));
            System.out.println(studentList.get(3));
        }).start();
        new Thread(() ->
        {
            System.out.println(studentList.get(4));
            System.out.println(studentList.get(5));
        }).start();
    }

    public void getAllStudentSynchronized() {
        List<Student> studentList = studentRepository.findAll();
        printStudent(studentList.get(0));
        printStudent(studentList.get(1));

        new Thread(() ->
        {
            printStudent(studentList.get(2));
            printStudent(studentList.get(3));
        }).start();
        new Thread(() ->
        {
            printStudent(studentList.get(4));
            printStudent(studentList.get(5));
        }).start();
    }

    private synchronized void printStudent(Student student) {
        System.out.println(student);
    }
}

