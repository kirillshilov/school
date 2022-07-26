package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;


@RestController
@RequestMapping("student")
public class StudentController {
    StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity editStudent(@RequestBody Student student) {
        Student temp = studentService.changeStudent(student);
        if (temp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student temp = studentService.findStudent(id);
        if (temp == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @GetMapping()
    public Collection<Student> getStudentByAge(@RequestParam(required = false) Long age, @RequestParam(required = false) Long minAge, @RequestParam (required = false) Long maxAge) {


       if (minAge != null && maxAge != null) {
           return studentService.allStudentBetweenAge(minAge, maxAge);
       }
          return studentService.allStudentOfAge(age);
    }

}
