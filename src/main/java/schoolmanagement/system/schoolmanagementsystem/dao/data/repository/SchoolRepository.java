package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.School;

import java.util.Optional;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {
//    School findSchoolById(String schoolId);
    Optional<School> findSchoolById(String schoolId);
}
