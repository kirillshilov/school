package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class SchoolApplicationTests {
    @LocalServerPort
    private int port;
@Autowired
 StudentController studentController;
@Autowired
    FacultyController facultyController;
@Autowired
TestRestTemplate testRestTemplate;

@Test
    public  void contextLoads( ) throws Exception{
    Assertions.assertThat(studentController).isNotNull();
    Assertions.assertThat(facultyController).isNotNull();
}
}
