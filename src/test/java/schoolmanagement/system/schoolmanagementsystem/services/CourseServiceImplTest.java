package schoolmanagement.system.schoolmanagementsystem.services;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.CreateCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EnrollForCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.FindAllCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdateCourseRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseServiceImplTest {
    @Autowired
    CourseService courseServices ;
     Course savedCourse;

    @BeforeEach
    void setUp() {
        CreateCourseRequest createCourseRequest = CreateCourseRequest
                .builder()
                .courseName("Pythons")
                .courseTitle("Introduction To Pythons")
                .courseCode("1011")
                .courseStatus(CourseStatus.ACTIVATED)
                .build();
        savedCourse =  courseServices.createCourse(createCourseRequest);
    }

    @AfterEach
    void tearDown() {

        courseServices.deleteAll();
    }


    @Test
    public void CourseCanBeCreated(){
        Course newCourse = Course.builder()
                .courseName("Pythons")
                .courseCode("101")
                .courseStatus(CourseStatus.ACTIVATED)
                .build();
        assertEquals("Pythons", newCourse.getCourseName());
    }
    @Test
    public void CourseCanBeCreate(){
        assertEquals(1, courseServices.size());
    }

    @Test
    public void CourseCanBeFindById(){
        Course foundCourse = courseServices.findCourseById(savedCourse.getId());
        assertThat(foundCourse.getId()).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(savedCourse.getId());
    }

    @Test
    public void findCoursesByCourseName(){
        Course foundCourse = courseServices.findCourseByName(savedCourse.getCourseName());
        assertThat(foundCourse.getId()).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(savedCourse.getId());
    }

    @Test
    public void findCourseByCourseCode(){
        Course foundCourse = courseServices.findCourseByCode(savedCourse.getCourseCode());
        assertThat(foundCourse.getId()).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(savedCourse.getId());
    }

    @Test
    public void findCourseByCourseTitle(){
        Course foundCourse = courseServices.findCourseByTitle(savedCourse.getCourseTitle());
        assertThat(foundCourse.getId()).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(savedCourse.getId());
    }



    @Test
    public void findAllCourses(){
        FindAllCourseRequest findAllCourseRequest = FindAllCourseRequest.builder()
                .page(1)
                .limit(1)
                .build();
        List<Course> foundCourses = courseServices.findAllCourses(findAllCourseRequest.getLimit(), findAllCourseRequest.getPage());
        assertEquals("1011", foundCourses.get(0).getCourseCode());
        assertEquals("Introduction To Pythons", foundCourses.get(0).getCourseTitle());
        assertEquals("Pythons", foundCourses.get(0).getCourseName());
        assertEquals(CourseStatus.ACTIVATED, foundCourses.get(0).getCourseStatus());
    }

    @Test
    public void CourseCanBeDeleteById(){
      String deletedCourse =  courseServices.deleteById(savedCourse.getId());
        assertEquals(0, courseServices.size());
        assertEquals("Course successfully deleted", deletedCourse);

    }


    @Test
    public void deleteAllCourses(){
        courseServices.deleteAll();
        assertEquals(0, courseServices.size());
    }

    @Test
    public void disactivateCourse(){
       String disactivateCourse = courseServices.disactivateCourse(savedCourse.getCourseName());
        assertEquals("Course successfully Disactivated", disactivateCourse);
    }

    @Test
    public void activateCourse(){
        String activateCourse = courseServices.activateCourse(savedCourse.getCourseName());
        assertEquals("Course successfully Activated", activateCourse);
    }

    @Test
    public void findAllActivatedCourse(){
        List<Course> foundCourse = courseServices.findAllActivatedCourse();
        assertThat(foundCourse).isNotNull();
        assertNotNull(foundCourse);


    }

    @Test
    public void findAllDisactivatedCourse(){
        List<Course> foundCourse = courseServices.findAllDisactivatedCourse();
        assertThat(foundCourse).isNull();
        assertNull(foundCourse);

    }

    @Test
    public void updateCourseProfile(){
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .courseName("Java")
                .courseCode("Jav102")
                .courseTitle("Introduction to Java Programming language")
                .courseId(savedCourse.getId())
                .courseStatus(CourseStatus.DISACTIVATED)
                .build();
        Course updatedCourse = courseServices.updateCourseProfile(updateCourseRequest, savedCourse.getId());
        assertEquals("Java", updatedCourse.getCourseName());
        assertEquals("Introduction to Java Programming language", updatedCourse.getCourseTitle());
        assertEquals("Jav102", updatedCourse.getCourseCode());
        assertEquals(CourseStatus.DISACTIVATED, updatedCourse.getCourseStatus());

    }




//    @Test
//    public void  enrollForCourse(){
//        EnrollForCourseRequest enrollForCourseRequest = EnrollForCourseRequest.builder()
////                .courseId(savedCourse.getId())
//                .courseName(savedCourse.getCourseName())
//                .courseCode(savedCourse.getCourseCode())
//                .courseTitle(savedCourse.getCourseTitle())
//                .build();
//        Course course = courseServices.enrollForCourse(enrollForCourseRequest);
//        assertThat(course.getId()).isNotNull();
//        assertEquals("Pythons",course.getCourseName());
//        assertEquals("Introduction To Pythons",course.getCourseTitle());
//        assertEquals("1011",course.getCourseCode());
//
//
//    }



}
