package ru.hogwarts.school.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepositori extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);
}
