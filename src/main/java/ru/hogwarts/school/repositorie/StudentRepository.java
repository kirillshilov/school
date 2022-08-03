package ru.hogwarts.school.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Set<Student> findStudentByAge(int age);

    Set <Student> findByAgeBetween(int minAge, int maxAge);

}
