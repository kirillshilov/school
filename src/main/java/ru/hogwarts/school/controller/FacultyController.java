package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    FacultyService facultyService;


    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException();
        }
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity readFaculty(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.readFaculty(id);
        if (!faculty.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity putFaculty(@RequestBody Faculty faculty) {
        Faculty temp = facultyService.putFaculty(faculty);
        if (temp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        Optional<Faculty> temp = facultyService.deleteFaculty(id);

        if (temp.isPresent()) {
            return ResponseEntity.ok(temp);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping(params = "color")
    public Collection <Faculty> getFacultyByColor(@RequestParam(required = false) String color) {
        return facultyService.allFacultyOfColor(color);
    }

    @GetMapping(params = "colorOrName")
    public Collection<Faculty> getFacultyByColorOrName(@RequestParam(required = false) String colorOrName) {
        return facultyService.allFacultyOfColorOrName(colorOrName);
    }
}
