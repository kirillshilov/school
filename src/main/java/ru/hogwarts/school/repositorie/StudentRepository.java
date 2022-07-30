package ru.hogwarts.school.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List <Student> findByAge(Long age);

    List <Student> findByAgeBetween(Long minAge, Long maxAge);
}
