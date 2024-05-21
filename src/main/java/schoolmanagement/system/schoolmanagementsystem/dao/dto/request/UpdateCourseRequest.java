package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateCourseRequest {
    private String schoolId;
    private String courseId;
    private String courseTitle;
    private String courseName;
    private String courseCode;
    private CourseStatus courseStatus;
}
