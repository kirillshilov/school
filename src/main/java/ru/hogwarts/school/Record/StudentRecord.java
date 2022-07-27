package ru.hogwarts.school.Record;

import ru.hogwarts.school.model.Faculty;

public class StudentRecord {
    private Long id;
    private String name;
    private int age;
    private Faculty faculty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyRecord facultyRecord) {
        this.faculty = faculty;
    }

}
