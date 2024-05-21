package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseResponse {
    private String schoolId;
    private String courseCode;
    private String CourseTitle;
    private String message;
    private String courseId;
    private String courseName;

}
