package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("student")
public class StudentController {
    StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/studentWithA")
    public Collection<String> getStartWithAStudent() {
        return studentService.getStartWithAStudent();

    }

    @GetMapping("/avgAgeOfStudent")
    public Integer getAvgAgeOfStudent() {

    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity readStudent(@PathVariable Long id) {
        Optional<Student> temp = studentService.readStudent(id);
        if (!temp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp.get());
    }

    @PutMapping
    public ResponseEntity putStudent(@RequestBody Student student) {
        Student temp = studentService.putStudent(student);
        if (temp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }


    @GetMapping(params = {"age"})
    public Collection<Student> getStudentByAge(@RequestParam(required = false) int age) {
        return studentService.allStudentOfAge(age);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public Collection<Student> getStudentByAgeBetween(@RequestParam(value = "minAge", required = false) int minAge, @RequestParam(value = "maxAge", required = false) int maxAge) {

        return studentService.allStudentBetweenAge(minAge, maxAge);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity getFacultyOfStudent(@RequestParam(required = false) Long id) {
        if (studentService.getStudentFaculty(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.getStudentFaculty(id));
    }


}
