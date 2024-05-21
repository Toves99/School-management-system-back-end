package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, java.lang.String> {
    Optional<Teacher>findTeacherByFirstName(String teacherName);
}
