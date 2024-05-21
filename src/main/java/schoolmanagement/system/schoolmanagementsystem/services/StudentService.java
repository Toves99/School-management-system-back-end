package schoolmanagement.system.schoolmanagementsystem.services;


import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Enrollment;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Student;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.AdmitStudentRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EnrollForCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedStudentProfileRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.CreateCourseResponse;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.EnrollCourseResponse;

import java.util.List;

public interface StudentService {

    Student admitstudent(AdmitStudentRequest admitStudentRequest);

    long size();

    Student findStudentById(String id);

    String deleteAll();


    Student findStudentByEmail(String email);

    Student findStudentByName(String studentFirstName);

    String deleteStudentById(String id);

    Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest);

    List<Student> findAllStudent(int page, int limit);

//    Enrollment enrollStudentInCourse(String studentId, String courseId);

    Student enrollStudentInCourses(String studentId, String courseId);




//    void studentCanEnrollInCourse(String studentId, String courseId);
//    String enrollStudentInCourse(EnrollForCourseRequest enrollForCourseRequest);






}
