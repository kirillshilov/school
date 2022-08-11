package ru.hogwarts.school.repositorie;


import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.List;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByStudentId(long studentId);


    List <Avatar> findAllByDataNotNull();
}
