package ru.hogwarts.school.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;

public interface AvatarRepository extends JpaRepository <Avatar, Long > {
    public Avatar findAvatarByStudentId (Long Id);
}
