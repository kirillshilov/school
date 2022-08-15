package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.AvatarRepository;
import ru.hogwarts.school.repositorie.StudentRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    @Value("&{path.to.avatars.folder}")
    private String avatarsDir;
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.debug("method uploadAvatar is started");
        Student student = studentRepository.getReferenceById(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(long studentId) {
        logger.debug("method findAvatar is started");
        Avatar avatar = new Avatar();
        avatar = avatarRepository.findByStudentId(studentId);
        return avatar;
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.debug("method getAllAvatars is started");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        return avatarRepository.findAll(pageRequest).getContent();
    }
}

