package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositorie.FacultyRepositori;


import java.util.*;

@Service
public class FacultyService {
    FacultyRepositori facultyRepositori;

    public FacultyService(FacultyRepositori facultyRepositori) {
        this.facultyRepositori = facultyRepositori;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepositori.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepositori.findById(id).get();
    }

    public Faculty changeFaculty(Faculty faculty) {
        return facultyRepositori.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepositori.deleteById(id);
    }

    public Collection<Faculty> allFacultyOfColorOrName(String color, String name) {
        return facultyRepositori.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
