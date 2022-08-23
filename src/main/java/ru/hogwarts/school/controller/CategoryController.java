package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student_by_category")
public class CategoryController {
    private final StudentService studentService;

    public CategoryController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Long getAmountAllStudent() {
        return studentService.getCountOfStudent();
    }

    @GetMapping("/AvgAge")
    public Double getAvgAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/last_students")
    public List<Student> getLastStudents() {
        return studentService.getLastStudents();

    }
}
