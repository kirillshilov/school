//package ru.hogwarts.school.controller;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.repositorie.FacultyRepository;
//import ru.hogwarts.school.repositorie.StudentRepository;
//import ru.hogwarts.school.service.StudentService;
//
//import java.util.Optional;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(MockitoExtension.class)
//class StudentControllerTest {
//    @LocalServerPort
//    private int port;
//    @Autowired
//    private StudentController studentController;
//    @Autowired
//    TestRestTemplate testRestTemplate;
//    @MockBean
//    StudentRepository studentRepositoryMock;
//    @MockBean
//    FacultyRepository facultyRepositoryMock;
//    @InjectMocks
//    StudentService studentService = new StudentService(studentRepositoryMock, facultyRepositoryMock);
//
//    @Test
//    void contextLoads() throws Exception {
//        Assertions.assertThat(studentController).isNotNull();
//    }
//
//    @Test
//    void createStudent() {
//        Student studentTemp = new Student();
//        Faculty facultyTemp = new Faculty();
//        facultyTemp.setId(1L);
//        facultyTemp.setColor("Green");
//        facultyTemp.setName("Griffindor");
//        studentTemp.setId(1L);
//        studentTemp.setName("Ron");
//        studentTemp.setAge(15);
//        studentTemp.setFaculty(facultyTemp);
//        Mockito.when(studentRepositoryMock.save(studentTemp)).thenReturn(studentTemp);
//        Assertions.assertThat(this.testRestTemplate.postForEntity("http://localhost:" + port + "/student", studentTemp, studentTemp.getClass()));
//
//    }
////
////    @Test
////    void readStudent() {
////        Student studentTemp = new Student();
////        Faculty facultyTemp = new Faculty();
////        facultyTemp.setId(1L);
////        facultyTemp.setColor("Green");
////        facultyTemp.setName("Griffindor");
////        studentTemp.setId(1L);
////        studentTemp.setName("Ron");
////        studentTemp.setAge(15);
////        studentTemp.setFaculty(facultyTemp);
////        Mockito.when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(studentTemp));
////        Assertions.assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/student" + 1, studentTemp.getClass())).isIn(studentTemp);
////    }
//
//    @Test
//    void putStudent() {
//
//    }
//
//    @Test
//    void deleteStudent() {
//    }
//
//    @Test
//    void getStudentByAge() {
//    }
//
//    @Test
//    void getStudentByAgeBetween() {
//    }
//
//    @Test
//    void getFacultyOfStudent() {
//    }
//}