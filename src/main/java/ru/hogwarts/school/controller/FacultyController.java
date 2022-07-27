package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Record.FacultyRecord;
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
    public FacultyRecord createFaculty(@RequestBody FacultyRecord facultyRecord) {
        return facultyService.addFaculty(facultyRecord);
    }

    @PutMapping
    public ResponseEntity editFaculty(@RequestBody FacultyRecord facultyRecord) {
        FacultyRecord temp = facultyService.changeFaculty(facultyRecord);
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

    @GetMapping(params = "color")
    public Collection <FacultyRecord> getFacultyByColor(@RequestParam  String color) {
        return facultyService.allFacultyOfColor(color);
    }
    @GetMapping()
    public Collection <FacultyRecord> getFacultyByColorOrName(@RequestParam  String colorOrName) {
        return facultyService.allFacultyOfColorOrName(colorOrName);
    }
}
