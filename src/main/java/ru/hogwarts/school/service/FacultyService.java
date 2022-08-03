package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.FacultyRepository;


import java.util.*;


@Service
public class FacultyService {
    FacultyRepository facultyRepositori;
    public FacultyService(FacultyRepository facultyRepositori) {
        this.facultyRepositori = facultyRepositori;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepositori.save(faculty);
    }

    public Optional <Faculty> readFaculty(Long id) {
if (facultyRepositori.findById(id).isPresent()){
    return facultyRepositori.findById(id);
}
        return Optional.empty();
    }

    public Faculty putFaculty(Faculty faculty) {
if (facultyRepositori.findById(faculty.getId()).isPresent()){
     return facultyRepositori.save(faculty);
}
return null;
    }

    public Optional <Faculty> deleteFaculty(Long id) {
        Optional <Faculty> faculty = facultyRepositori.findById(id);
if (faculty.isPresent()){
     facultyRepositori.deleteById (id);
     return faculty;
}
        return faculty;
    }

    public Collection<Faculty> allFacultyOfColor(String color) {
return facultyRepositori.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> allFacultyOfColorOrName(String colorOrName) {
return facultyRepositori.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }
    public Collection <Student> getAllStudentsOfFaculty (Long id){

return facultyRepositori.getReferenceById(id).getStudents();
    }

}
