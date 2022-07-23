package ru.hogwarts.school.service;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositorie.FacultyRepositori;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class FacultyServiceTest {
    FacultyRepositori facultyRepositorimock = Mockito.mock(FacultyRepositori.class);
    FacultyService facultyService = new FacultyService(facultyRepositorimock);

    @Test
    void addFaculty() {

        Faculty faculty = new Faculty(1L, "aaa", "green");
        Mockito.when(facultyRepositorimock.save(faculty)).thenReturn(faculty);
        Faculty stu = facultyService.addFaculty(faculty);
        assertEquals(faculty, stu);
    }
    @Test
    void changeFaculty() {
        Faculty faculty = new Faculty(1L, "aaa", "green");
        Mockito.when(facultyRepositorimock.save(faculty)).thenReturn(faculty);
        assertEquals(faculty, facultyService.changeFaculty(faculty));
        assertNull(facultyService.changeFaculty(new Faculty(44L, "String", "23")));
    }

    @Test
    void deleteFaculty() {
        Faculty faculty = new Faculty(1L, "aaa", "green");
        facultyService.addFaculty(faculty);

    }

    @Test
    void allFacultyOfColor() {
       Faculty faculty = new Faculty(1L, "aaa", "green");
       List <Faculty> facultyMap = new ArrayList<>();
        assertEquals(facultyMap, facultyService.allFacultyOfColorOrName("green", null));
        Mockito.when(facultyRepositorimock.findByColorIgnoreCaseOrNameIgnoreCase("1", null)).thenReturn(facultyMap);
        assertEquals(facultyMap, facultyService.allFacultyOfColorOrName("1", null));
       facultyService.addFaculty(faculty);

    }
}