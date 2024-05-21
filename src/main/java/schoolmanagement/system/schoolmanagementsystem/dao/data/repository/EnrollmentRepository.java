package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Enrollment;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {


}
