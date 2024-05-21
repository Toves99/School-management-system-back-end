package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnrollForCourseRequest {
    private String studentId;
    private String courseId;
//    private String courseName;
//    private String courseTitle;
//    private String courseCode;
}
