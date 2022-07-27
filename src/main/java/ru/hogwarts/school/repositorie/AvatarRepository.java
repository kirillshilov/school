package ru.hogwarts.school.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;
@Repository
public interface AvatarRepository extends JpaRepository <Avatar, Long > {
    public Avatar findAvatarByStudentId (Long Id);
}
