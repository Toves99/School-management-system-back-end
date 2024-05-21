package schoolmanagement.system.schoolmanagementsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.TeacherRepository;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EmployTeacherRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedTeacherProfileRequest;
import schoolmanagement.system.schoolmanagementsystem.exception.TeacherDoesNotExistException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    @Override
    public Teacher addTeacher(EmployTeacherRequest employTeacherRequest) {
        Teacher teacher1 = Teacher.builder()
                .firstName(employTeacherRequest.getFirstName())
                .lastName(employTeacherRequest.getLastName())
                .gender(employTeacherRequest.getGender())
                .dateOfBirth(employTeacherRequest.getDateOfBirth())
                .email(employTeacherRequest.getEmail())
                .roleHashSet(new HashSet<>())
                .build();
         return teacherRepository.save(teacher1);

    }

    @Override
    public Teacher findTeacherById(String id) {
        Optional<Teacher> school = teacherRepository.findById(id);
        if (school.isPresent()) {
            return teacherRepository.findById(id).orElseThrow(
                    () -> new TeacherDoesNotExistException(TeacherDoesNotExistException.TeacherDoesNotExist(id))
            );
        }
        return null;
    }

    @Override
    public Teacher findTeacherByName(String firstName) {
        Optional<Teacher> school = teacherRepository.findTeacherByFirstName(firstName);
        if (school.isPresent()) {
            return teacherRepository.findTeacherByFirstName(firstName).orElseThrow(
                    () -> new TeacherDoesNotExistException(TeacherDoesNotExistException.TeacherDoesNotExist(firstName, null))
            );
        }
        return null;
    }


    @Override
    public List<Teacher> findAllTeachers(int limit, int page) {
        List<Teacher> teachers = new ArrayList<>();
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Teacher> teachers1 = teacherRepository.findAll(pageable);
        List<Teacher> teachers2 = teachers1.getContent();
        for (Teacher teacher : teachers2) {
            Teacher teacher1 = new Teacher();
            BeanUtils.copyProperties(teacher, teacher1);
           teachers.add(teacher1);
        }
        return teachers;
    }

    @Override
    public String deleteAll() {
        teacherRepository.deleteAll();;
        return "All teachers sucessfully deleted";
    }

    @Override
    public long size() {
        return teacherRepository.count();
    }

    @Override
    public String deleteTeachersById(String id) {
     Optional<Teacher> foundTeacher =  teacherRepository.findById(id);
     if(foundTeacher.isPresent()){
         teacherRepository.deleteById(id);
         return "Teacher with " + id + " successfully deleted";
        }
        throw new TeacherDoesNotExistException(TeacherDoesNotExistException.TeacherDoesNotExist(id));

    }

    @Override
    public Teacher updateTeachersProfile(UpdatedTeacherProfileRequest updatedProfileRequest) {
        Optional<Teacher> foundTeacher= teacherRepository.findById(updatedProfileRequest.getTeacherId());
        if (foundTeacher.isPresent()) {
                foundTeacher.get().setEmail(updatedProfileRequest.getEmail());
                foundTeacher.get().setFirstName(updatedProfileRequest.getFirstName());
                foundTeacher.get().setLastName(updatedProfileRequest.getLastName());
                foundTeacher.get().setGender(updatedProfileRequest.getGender());
                foundTeacher.get().setDateOfBirth(updatedProfileRequest.getDateOfBirth());
                return teacherRepository.save(foundTeacher.get());
        }
        else {
            throw new TeacherDoesNotExistException(TeacherDoesNotExistException.TeacherDoesNotExist(updatedProfileRequest.getTeacherId()));

        }
    }


}
