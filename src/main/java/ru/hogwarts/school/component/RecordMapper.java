package ru.hogwarts.school.component;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.Record.FacultyRecord;
import ru.hogwarts.school.Record.StudentRecord;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@Component
public class RecordMapper {
    public FacultyRecord toRecord (Faculty faculty){
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;
    }
     public StudentRecord toRecord (Student student){
         StudentRecord studentRecord = new StudentRecord();
         studentRecord.setId(student.getId());
         studentRecord.setName(student.getName());
         studentRecord.setAge(student.getAge());
         if (student.getFaculty() != null){
         studentRecord.setFaculty(toRecord(student.getFaculty()));}
         return studentRecord;
     }


    public Student toEntity (StudentRecord studentRecord){
    Student student = new Student();
    student.setAge(studentRecord.getAge());
    student.setName(studentRecord.getName());
    return student;
    }

    public Faculty toEntity (FacultyRecord facultyRecord){
        Faculty faculty = new Faculty();
        faculty.setColor(facultyRecord.getColor());
        faculty.setName(facultyRecord.getName());
        return faculty;
    }
    
}
