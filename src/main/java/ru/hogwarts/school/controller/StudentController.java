package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.Record.StudentRecord;
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
    public StudentRecord createStudent(@RequestBody StudentRecord studentRecord) {
        return studentService.addStudent(studentRecord);
    }

    @PutMapping
    public ResponseEntity editStudent(@RequestBody StudentRecord studentRecord) {
        StudentRecord temp = studentService.changeStudent(studentRecord);
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
        StudentRecord temp = studentService.findStudent(id);
        if (temp == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @GetMapping()
    public Collection <StudentRecord> getStudentByAge(@PathVariable Long age) {
          return studentService.allStudentOfAge(age);
    }
    @GetMapping(params = {"min", "max"})
    public Collection<StudentRecord> getStudentByAgeBetween( @RequestParam Long minAge, @RequestParam Long maxAge) {

            return studentService.allStudentBetweenAge(minAge, maxAge);
        }


}
