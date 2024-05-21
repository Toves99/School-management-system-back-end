package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Role;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.RoleType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterSchoolResponse {
    private String schoolId;
    private String courseId;
    private RoleType roleType;

    private String message;
    private String schoolName;
    private String schoolLocation;


    private String email;




}


