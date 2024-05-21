package schoolmanagement.system.schoolmanagementsystem.services;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EmployTeacherRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedTeacherProfileRequest;

import java.util.List;

public interface TeacherService {
    Teacher addTeacher(EmployTeacherRequest employTeacherRequest);

    Teacher findTeacherById(String id);

    Teacher findTeacherByName(String firstName);

    List<Teacher> findAllTeachers(int limit, int page);

   String deleteAll();

    long size();



    String deleteTeachersById(String id);

    Teacher updateTeachersProfile(UpdatedTeacherProfileRequest updatedProfileRequest);
}
