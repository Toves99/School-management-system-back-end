package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnrollCourseResponse {
    private String message;
    private String courseName;
    private String studentId;

}
