package ru.hogwarts.school.service;


import org.apache.logging.log4j.util.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.FacultyRepository;


import javax.naming.Name;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FacultyService {
    FacultyRepository facultyRepositori;

    public FacultyService(FacultyRepository facultyRepositori) {
        this.facultyRepositori = facultyRepositori;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("method creatFaculty is Started");
        return facultyRepositori.save(faculty);
    }

    public Optional<Faculty> readFaculty(Long id) {
        logger.debug("method readFaculty is Started");
        if (facultyRepositori.findById(id).isPresent()) {
            logger.debug("faculty is found");
            return facultyRepositori.findById(id);

        }
        logger.error("faculty not Found");
        return Optional.empty();
    }

    public Faculty putFaculty(Faculty faculty) {
        if (facultyRepositori.findById(faculty.getId()).isPresent()) {
            logger.warn("faculty changed");
            return facultyRepositori.save(faculty);
        }
        logger.error("faculty not Found");
        return null;
    }

    public Optional<Faculty> deleteFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepositori.findById(id);
        if (faculty.isPresent()) {
            facultyRepositori.deleteById(id);
            logger.warn("faculty deleted");
            return faculty;
        }
        logger.error("faculty not Found");
        return faculty;
    }

    public Collection<Faculty> allFacultyOfColor(String color) {
        logger.debug("method allFacultyOfColor is Started");
        return facultyRepositori.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> allFacultyOfColorOrName(String colorOrName) {
        logger.debug("method allFacultyOfColorOrName is Started");
        return facultyRepositori.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }

    public Collection<Student> getAllStudentsOfFaculty(Long id) {
        logger.debug("method getAllStudentsOfFaculty is Started");
        return facultyRepositori.findById(id).orElseThrow().getStudents();
    }

    public String getLongestNameOfFaculty() {
        return facultyRepositori.findAll()
                .stream()
                .map(Faculty::getName)
                .max(String::compareTo)
                .orElseThrow();
    }
}
