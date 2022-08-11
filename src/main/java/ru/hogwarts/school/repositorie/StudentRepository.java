package ru.hogwarts.school.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import ru.hogwarts.school.model.StudentCount;


import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Set<Student> findStudentByAge(int age);

    Set<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT count (*) from student", nativeQuery = true)
    Long getCountStudent();

    @Query(value = "SELECT AVG (age) from student", nativeQuery = true)
    Long getAvgAgeOfStudent();

    @Query(value = "SELECT * from student ORDER BY id DESC LIMIT 5 ", nativeQuery = true)
    List<Student> getLastStudents();
}
