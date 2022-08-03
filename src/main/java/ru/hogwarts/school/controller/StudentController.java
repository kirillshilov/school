package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.hogwarts.school.model.Faculty;
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

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @GetMapping("{id}")
    public ResponseEntity readStudent( @PathVariable Long id) {
       Optional <Student> temp = studentService.readStudent(id);
        if (!temp.isPresent()) {
          return   ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
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
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping(params = {"age"})
    public Collection <Student> getStudentByAge(@RequestParam (required = false) Long age) {
          return studentService.allStudentOfAge(age);
    }
    @GetMapping(params = {"min", "max"})
    public Collection<Student> getStudentByAgeBetween( @RequestParam (required = false) Long minAge, @RequestParam (required = false) Long maxAge) {

            return studentService.allStudentBetweenAge(minAge, maxAge);
        }
    @GetMapping (params = {"studentId"})
    public ResponseEntity getFacultyOfStudent(@RequestParam (required = false) Long id){

      Faculty faculty =  studentService.getStudentFaculty(id);
    if (faculty == null){
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(faculty);
    }

}
