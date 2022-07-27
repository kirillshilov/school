package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Record.FacultyRecord;
import ru.hogwarts.school.Record.StudentRecord;
import ru.hogwarts.school.Exceptions.FacultyNotFoundException;
import ru.hogwarts.school.Exceptions.StudentNotFoundException;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {
    StudentRepository studentRepository;
    RecordMapper recordMapper;

    public StudentService(StudentRepository studentRepository, RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
    }
    public StudentRecord addStudent(StudentRecord studentRecord) {
        return recordMapper.toRecord(studentRepository.save(recordMapper.toEntity(studentRecord)));

    }

    public StudentRecord findStudent(Long id) {
        return studentRepository.findById(id)
                .map(recordMapper::toRecord)
                .orElseThrow(StudentNotFoundException::new);
    }

    public StudentRecord changeStudent(StudentRecord studentRecord) {
        Student oldStudent = studentRepository.findById(studentRecord.getId()).orElseThrow(StudentNotFoundException::new);
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setName(studentRecord.getName());
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        recordMapper.toRecord(student);
    }

    public Collection<StudentRecord> allStudentOfAge(Long age) {
        return studentRepository.findByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> allStudentBetweenAge(Long minAge, Long maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getStudentFaculty(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (student.getFaculty() == null) {
            throw new FacultyNotFoundException();
        }
        return recordMapper.toRecord(student.getFaculty());
    }
}

