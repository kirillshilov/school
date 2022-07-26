package ru.hogwarts.school.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepositori extends JpaRepository<Student, Long> {
    List <Student> findByAge(Long age);

    List <Student> findByAgeBetween(Long minAge, Long maxAge);
}
