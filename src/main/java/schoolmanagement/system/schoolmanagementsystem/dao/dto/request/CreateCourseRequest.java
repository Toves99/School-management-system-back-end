package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.CourseRepository;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateCourseRequest {
    private String schoolId;
    private String studentId;
    private String courseCode;
    private String courseTitle;

    private String courseName;
    private CourseStatus courseStatus;
}
