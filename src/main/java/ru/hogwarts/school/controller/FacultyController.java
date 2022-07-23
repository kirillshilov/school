package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    FacultyService facultyService;


    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity editFaculty(@RequestBody Faculty faculty) {
        Faculty temp = facultyService.changeFaculty(faculty);
        if (temp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable Long id) {
        facultyService.findFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public Collection<Faculty> getFacultyByColorOrName(@RequestParam (required = false) String color, @RequestParam (required = false) String name) {
        return facultyService.allFacultyOfColorOrName(color, name);
    }
}
