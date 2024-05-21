package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.RoleType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdmitStudentResponse {
    private String schoolName;
    private String firstName;
    private String message;
    private RoleType roleType;
    private String studentId;
}
