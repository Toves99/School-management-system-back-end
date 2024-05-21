package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findCourseById(String courseId);
    Optional<Course> findCourseByCourseName(String courseName);
    Optional<Course> findCourseByCourseCode(String courseCode);
    Optional<Course> findCourseByCourseTitle(String courseTitle);
    List<Course> findCourseByCourseStatus(CourseStatus courseStatus);



}
