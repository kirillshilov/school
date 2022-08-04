package ru.hogwarts.school.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
    Avatar findByStudentId(long studentId);
}
