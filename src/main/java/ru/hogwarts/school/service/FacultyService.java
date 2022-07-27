package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyNotFoundException;

import ru.hogwarts.school.Record.FacultyRecord;
import ru.hogwarts.school.Record.StudentRecord;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.repositorie.FacultyRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    FacultyRepository facultyRepositori;
    RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepositori, RecordMapper recordMapper) {
        this.facultyRepositori = facultyRepositori;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord addFaculty(FacultyRecord facultyRecord) {
        return recordMapper.toRecord(facultyRepositori.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord findFaculty(Long id) {
        return facultyRepositori.findById(id)
                .map(recordMapper::toRecord)
                .orElseThrow(FacultyNotFoundException::new);

    }

    public FacultyRecord changeFaculty(FacultyRecord facultyRecord) {
        Faculty oldFaculty = facultyRepositori.findById(facultyRecord.getId()).orElseThrow(FacultyNotFoundException::new);
        oldFaculty.setName(facultyRecord.getName());
        oldFaculty.setColor(facultyRecord.getColor());
        return recordMapper.toRecord(facultyRepositori.save(oldFaculty));
    }

    public void deleteFaculty(Long id) {
     Faculty faculty = facultyRepositori.findById(id).orElseThrow(FacultyNotFoundException::new);
     facultyRepositori.delete(faculty);
     return recordMapper.toRecord(faculty);
    }

    public Collection<FacultyRecord> allFacultyOfColor(String color) {
        return facultyRepositori.findByColorIgnoreCase(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> allFacultyOfColorOrName(String colorOrName) {
        return facultyRepositori.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName , colorOrName).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
    public List <StudentRecord> getFacultyStudents (Long id){
        return facultyRepositori.findById(id)
                .orElseThrow(FacultyNotFoundException::new)
                .getStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

}
