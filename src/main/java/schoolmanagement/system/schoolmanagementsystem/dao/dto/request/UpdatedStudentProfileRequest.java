package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdatedStudentProfileRequest {
    private String schoolId;
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String emailAddress;
    private String studentAge;
    private String studentGender;
}
