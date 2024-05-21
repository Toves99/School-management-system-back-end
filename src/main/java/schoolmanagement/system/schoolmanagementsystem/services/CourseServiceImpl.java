package schoolmanagement.system.schoolmanagementsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.CourseRepository;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.CreateCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdateCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.exception.CourseDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(CreateCourseRequest createCourseRequest) {
//        if (courseRepository.findCourseByCourseName(createCourseRequest.getCourseName()).isPresent()) {
//            throw new CourseExistException("Course already exist");
//        } else {
            Course newCourse = Course
                    .builder()
                    .courseName(createCourseRequest.getCourseName())
                    .courseCode(createCourseRequest.getCourseCode())
                    .courseStatus(createCourseRequest.getCourseStatus())
                    .courseTitle(createCourseRequest.getCourseTitle())
                    .build();
            return courseRepository.save(newCourse);

//        }
    }

    @Override
    public String deleteAll() {
        courseRepository.deleteAll();
        return "All courses successfully deleted";
    }

    @Override
    public long size() {
        return courseRepository.count();
    }

    @Override
    public Course findCourseById(String id) {
        if (courseRepository.findCourseById(id).isPresent()) {
            return courseRepository.findCourseById(id).get();
        }
        throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(id));
    }

    @Override
    public Course findCourseByName(String courseName) {
        if (courseRepository.findCourseByCourseName(courseName).isPresent()) {
            return courseRepository.findCourseByCourseName(courseName).get();
        }

        throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseName));
    }

    @Override
    public Course findCourseByCode(String courseCode) {
        if (courseRepository.findCourseByCourseCode(courseCode).isPresent()) {
            return courseRepository.findCourseByCourseCode(courseCode).get();

        }
        throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseCode));
    }

    @Override
    public Course findCourseByTitle(String courseTitle) {
        if (courseRepository.findCourseByCourseTitle(courseTitle).isPresent()) {
            return courseRepository.findCourseByCourseTitle(courseTitle).get();

        }
        throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseTitle));
    }



    @Override
    public List<Course> findAllCourses(int limit, int page) {
        List<Course> courses = new ArrayList<>();
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Course> courses1 = courseRepository.findAll(pageable);
        List<Course> courses2 = courses1.getContent();
        for (Course course : courses2) {
            Course course1 = new Course();
            BeanUtils.copyProperties(course, course1);
            courses.add(course1);
        }
        return courses;
    }

    @Override
    public String deleteById(String courseId) {
        Optional<Course> foundCourse = courseRepository.findCourseById(courseId);
        if (foundCourse.isPresent()) {
            courseRepository.deleteById(courseId);
            return "Course successfully deleted";
        }
        throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseId));

    }
    @Override
    public String activateCourse(String courseName) {
        Course course = courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(() -> new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseName, null)));
        course.setCourseStatus(CourseStatus.ACTIVATED);
        courseRepository.save(course);
        return "Course successfully Activated";
    }

    @Override
    public String disactivateCourse(String courseName) {
        Course course = courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(() -> new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseName, null)));
        course.setCourseStatus(CourseStatus.DISACTIVATED);
        courseRepository.save(course);
        return "Course successfully Disactivated";
    }



    @Override
    public List<Course> findAllActivatedCourse() {
        List<Course> activatedCourses =  courseRepository.findCourseByCourseStatus(CourseStatus.ACTIVATED);
         return activatedCourses.isEmpty() ? null : activatedCourses;
    }

    @Override
    public List<Course> findAllDisactivatedCourse() {
        List<Course> disActivatedCourses =courseRepository.findCourseByCourseStatus(CourseStatus.DISACTIVATED);
        return disActivatedCourses.isEmpty() ? null : disActivatedCourses;
    }

    @Override
    public Course updateCourseProfile(UpdateCourseRequest updateCourseRequest, String courseId) {
        return updateCourse(updateCourseRequest, courseId);

    }



    private Course updateCourse(UpdateCourseRequest updateCourseRequest, String courseId) {
        Optional<Course> foundCourse = courseRepository.findCourseById(courseId);
        if (foundCourse.isPresent()) {
            if (updateCourseRequest.getCourseName() != null) {
                foundCourse.get().setCourseName(updateCourseRequest.getCourseName());
            }
            if (updateCourseRequest.getCourseCode() != null) {
                foundCourse.get().setCourseCode(updateCourseRequest.getCourseCode());
            }
            if (updateCourseRequest.getCourseStatus() != null) {
                foundCourse.get().setCourseStatus(updateCourseRequest.getCourseStatus());
            }
            if (updateCourseRequest.getCourseTitle() != null) {
                foundCourse.get().setCourseTitle(updateCourseRequest.getCourseTitle());
            }
            return courseRepository.save(foundCourse.get());
        }
        else {
            throw new CourseDoesNotExistException(CourseDoesNotExistException.CourseDoesNotExist(courseId));

        }


    }

}

