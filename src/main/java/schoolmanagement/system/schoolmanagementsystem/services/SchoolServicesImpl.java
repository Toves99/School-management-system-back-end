package schoolmanagement.system.schoolmanagementsystem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.SchoolRepository;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.*;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.*;
import schoolmanagement.system.schoolmanagementsystem.exception.SchoolDoesExistException;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class SchoolServicesImpl implements SchoolServices {

    private final SchoolRepository schoolRepository;
    private final RoleServices roleServices;
    private final TeacherService teacherService;


    private final StudentService studentService;
    private final CourseService courseService;


    @Override
    public RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest) {
//        if(schoolRepository.findSchoolBySchoolName(registerSchoolRequest.getSchoolName()).isPresent()){
//            throw new SchoolAlreadyExistException("School with name : " + registerSchoolRequest.getSchoolName() + "already exist");
//        }
        School newSchool = School.builder()
                .schoolName(registerSchoolRequest.getSchoolName())
                .email(registerSchoolRequest.getEmail())
                .schoolLocation(registerSchoolRequest.getSchoolLocation())
                .roleHashSet(new HashSet<>())
                .build();

        Role userRole = new Role(RoleType.ADMIN);
        userRole = roleServices.save(userRole);
        newSchool.getRoleHashSet().add(userRole);
        School foundSchool = schoolRepository.save(newSchool);


        return RegisterSchoolResponse.builder()
                .message("School successfully registered")
                .schoolId(foundSchool.getId())
                .schoolName(foundSchool.getSchoolName())
                .email(foundSchool.getEmail())
                .roleType(RoleType.ADMIN)
                .schoolLocation(foundSchool.getSchoolLocation())
                .build();


//        sendMail(customerRegister);
    }

    @Override
    public long size() {
        return schoolRepository.count();
    }

    @Override
    public School findSchoolById(String id) {
        Optional<School> school = schoolRepository.findSchoolById(id);
        if (school.isPresent()) {
            return schoolRepository.findById(id).orElseThrow(
                    () -> new SchoolDoesExistException("School Cannot be found")
            );
        }
        return null;

    }

    @Override
    public List<School> findAllSchool(int page, int limit) {
        List<School> schools = new ArrayList<>();
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<School> schools1 = schoolRepository.findAll(pageable);
        List<School> schools2 = schools1.getContent();
        for (School school : schools2) {
            School school1 = new School();
            BeanUtils.copyProperties(school, school1);
            schools.add(school1);
        }
        return schools;
    }

    @Override
    public String deleteById(String id) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(id);
        if (foundSchool.isPresent()) {
            schoolRepository.deleteById(id);
            return "School is Id" + id + " successfully deleted";
        }

        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(id));


    }

    @Override
    public String deleteAllSchools() {
        schoolRepository.deleteAll();
        return "Succesfully deleted";

    }

    @Override
    public School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest) {
        return updateSchool(updateSchoolProfileRequest);


    }


    private School updateSchool(UpdateSchoolProfileRequest updateSchoolProfileRequest) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(updateSchoolProfileRequest.getSchoolId());
        if (updateSchoolProfileRequest.getSchoolName() != null) {
            foundSchool.get().setSchoolName(updateSchoolProfileRequest.getSchoolName());
        }
        if (updateSchoolProfileRequest.getSchoolLocation() != null) {
            foundSchool.get().setSchoolLocation(updateSchoolProfileRequest.getSchoolLocation());
        }
        if (updateSchoolProfileRequest.getEmail() != null) {
            foundSchool.get().setEmail(updateSchoolProfileRequest.getEmail());
        }
        schoolRepository.save(foundSchool.get());
        return foundSchool.get();


    }


    @Override
    public AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest) {
        Student admittedStudent = studentService.admitstudent(admitStudentRequest);
        Optional<School> foundSchool = schoolRepository.findSchoolById(admitStudentRequest.getSchoolId());
        if (foundSchool.isPresent() && foundSchool.get().getStudents().contains(admittedStudent)) {
            foundSchool.get().getStudents().add(admittedStudent);

            Role userRole = new Role(RoleType.STUDENT);
            userRole = roleServices.save(userRole);
            admittedStudent.getRoleHashSet().add(userRole);
            schoolRepository.save(foundSchool.get());
        }

        return AdmitStudentResponse
                .builder()
                .roleType(RoleType.STUDENT)
                .schoolName(foundSchool.get().getSchoolName())
                .firstName(admittedStudent.getStudentFirstName())
                .message(admittedStudent.getStudentFirstName() + " " + admittedStudent.getStudentLastName() + " sucessfully admitted")
                .studentId(admittedStudent.getId())
                .build();
    }

    @Override
    public long sizeOfStudents() {
        return studentService.size();
    }

    @Override
    public Student findStudentById(String schoolId, String studentId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return studentService.findStudentById(studentId);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public Student findStudentByName(String schoolId, String firstName) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return studentService.findStudentByName(firstName);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public String deleteAllStudents() {
        studentService.deleteAll();
        return "All Student successfully deleted";
    }


    @Override
    public List<Student> findAllStudents(int limit, int page, java.lang.String schoolId) {
        List<Student> allStudents = studentService.findAllStudent(page, limit);
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return allStudents;

        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public String deleteStudentById(String schoolId, String studentId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            studentService.deleteStudentById(studentId);
            return "Student successfully deleted";
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest) {
        Student foundStudent = studentService.updateStudentProfile(updatedStudentProfileRequest);
        Optional<School> foundSchool = schoolRepository.findSchoolById(updatedStudentProfileRequest.getSchoolId());
        if (foundSchool.isPresent()) {
            foundSchool.get().getStudents().add(foundStudent);
            schoolRepository.save(foundSchool.get());

        }
        return UpdateStudentProfileResponse
                .builder()
                .id(foundStudent.getId())
                .firstName(foundStudent.getStudentFirstName())
                .message("Student profile successfully updated")
                .build();

    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        Course createdCourses = courseService.createCourse(createCourseRequest);
        Optional<School> foundSchool = schoolRepository.findSchoolById(createCourseRequest.getSchoolId());
        if (foundSchool.isPresent() && foundSchool.get().getCourses().contains(createdCourses)) {
            foundSchool.get().getCourses().add(createdCourses);
            schoolRepository.save(foundSchool.get());
        }
        return CreateCourseResponse.builder()
                .message("Course successfully created")
                .courseId(createdCourses.getId())
                .CourseTitle(createdCourses.getCourseTitle())
                .courseCode(createdCourses.getCourseCode())
                .schoolId(foundSchool.get().getId())
                .courseName(createdCourses.getCourseName())
                .build();

    }

    @Override
    public Course findCourseById(String courseId, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return courseService.findCourseById(courseId);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

    @Override
    public Course findCourseByName(java.lang.String courseName, java.lang.String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return courseService.findCourseByName(courseName);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

    @Override
    public Course findCourseByCourseCode(java.lang.String courseCode, java.lang.String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return courseService.findCourseByCode(courseCode);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

    @Override
    public Course findCourseByCourseTitle(String courseTitle, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return courseService.findCourseByTitle(courseTitle);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

    @Override
    public List<Course> findAllCourses(int limit, int page, String schoolId) {
        List<Course> allCourses = courseService.findAllCourses(page, limit);
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return allCourses;

        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public String deleteAllCourses() {
        courseService.deleteAll();
        return "All Courses successfully deleted";
    }

    @Override
    public long sizeOfCourses() {
        return courseService.size();
    }

    @Override
    public String deleteCourseById(String schoolId, String courseId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            courseService.deleteById(courseId);
            return "Course successfully deleted";
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest) {
        Course foundCourse = courseService.updateCourseProfile(updateCourseRequest, updateCourseRequest.getCourseId());
        Optional<School> foundSchool = schoolRepository.findSchoolById(updateCourseRequest.getSchoolId());
        if (foundSchool.isPresent()) {
            foundSchool.get().getCourses().add(foundCourse);
            schoolRepository.save(foundSchool.get());
        }
        return UpdateCourseResponse
                .builder()
                .message("Course profile successfully updated")
                .build();
    }

    @Override
    public String disactivateCourse(String courseName, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            courseService.disactivateCourse(courseName);
            return "Course successfully disactivated";
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public String activateCourse(String courseName, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            courseService.activateCourse(courseName);
            return "Course successfully activated";
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public List<Course> findAllActivatedCourse(java.lang.String schoolId) {
        List<Course> activatedCourses = courseService.findAllActivatedCourse();
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return activatedCourses;

        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public List<Course> findAllDisactivatedCourse(String schoolId) {
        List<Course> disActivatedCourses = courseService.findAllDisactivatedCourse();
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return disActivatedCourses;

        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }

    @Override
    public EmployedTeacherResponse employNewTeacherToSchool(EmployTeacherRequest employTeacherRequest) {
      Teacher employedTeacher =  teacherService.addTeacher(employTeacherRequest);
        Optional<School> foundSchool = schoolRepository.findSchoolById(employTeacherRequest.getSchoolId());
        if (foundSchool.isPresent() && foundSchool.get().getTeachers().contains(employedTeacher)) {
            foundSchool.get().getTeachers().add(employedTeacher);

            Role userRole = new Role(RoleType.TEACHER);
            userRole = roleServices.save(userRole);
            employedTeacher.getRoleHashSet().add(userRole);
            schoolRepository.save(foundSchool.get());
        }

        return EmployedTeacherResponse
                .builder()
                .message("Teacher details successfully added to school")
                .firstName(employedTeacher.getFirstName())
                .gender(employedTeacher.getGender())
                .lastName(employedTeacher.getLastName())
                .schoolId(foundSchool.get().getId())
                .teacherId(employedTeacher.getTeacherId())
                .dateOfBirth(employedTeacher.getDateOfBirth())
                .email(employedTeacher.getEmail())
                .build();
      }

    @Override
    public Teacher findTeacherById(String teacherId, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return teacherService.findTeacherById(teacherId);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

    @Override
    public List<Teacher> findAllTeachers(int limit, int page, String schoolId) {
        List<Teacher> allTeacher = teacherService.findAllTeachers(limit,page);
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return allTeacher;

        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }


    @Override
    public long sizeOfTeachers() {
        return teacherService.size();
    }

    @Override
    public String deleteAllTeachers() {
        teacherService.deleteAll();
        return "All Teachers successfully deleted";
    }

    @Override
    public String deleteTeacherById(String schoolId, String teacherId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            teacherService.deleteTeachersById(teacherId);
            return "Teacher successfully deleted";
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));
    }


    @Override
    public UpdateTeacherProfileResponse updateTeacherProfile(UpdatedTeacherProfileRequest updatedTeacherProfileRequest) {
        Teacher updatedTeacher = teacherService.updateTeachersProfile(updatedTeacherProfileRequest);
        Optional<School> foundSchool = schoolRepository.findSchoolById(updatedTeacherProfileRequest.getSchoolId());
        if (foundSchool.isPresent()) {
            foundSchool.get().getTeachers().add(updatedTeacher);
            schoolRepository.save(foundSchool.get());
        }
        return UpdateTeacherProfileResponse
                .builder()
                .firstName(updatedTeacher.getFirstName())
                .message("Teacher profile successfully updated")
                .id(updatedTeacher.getTeacherId())
                .build();

    }

    @Override
    public Teacher findTeacherByName(String firstName, String schoolId) {
        Optional<School> foundSchool = schoolRepository.findSchoolById(schoolId);
        if (foundSchool.isPresent()) {
            return teacherService.findTeacherByName(firstName);
        }
        throw new SchoolDoesExistException(SchoolDoesExistException.SchoolDoesExistException(schoolId));

    }

}
















