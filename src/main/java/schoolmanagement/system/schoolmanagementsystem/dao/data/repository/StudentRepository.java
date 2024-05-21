package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentById(String studentId);
    Optional<Student> findStudentByEmail(String studentEmail);
    Optional<Student> findStudentByStudentFirstName(String studentFirstName);
}
