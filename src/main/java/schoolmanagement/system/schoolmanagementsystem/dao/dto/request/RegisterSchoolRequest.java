package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Role;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.RoleType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterSchoolRequest {
    private String id;
    private RoleType roles;
    private String schoolName;
    private String email;
    private String schoolLocation;



}
