package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
@Document("Enrollment")
public class Enrollment {
    private String id;
    private Student student;
    private Course course;
    private Date enrollmentDate;

    public Enrollment(String studentId, String courseId, Date enrollmentDate) {

    }
    // getters and setters
}
