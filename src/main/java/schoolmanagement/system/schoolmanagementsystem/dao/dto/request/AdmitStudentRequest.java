package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdmitStudentRequest {
    private String schoolId;
    private String studentFirstName;
    private String studentLastName;
    private String emailAddress;
    private String studentAge;
    private String  gender;
    private String password;

}
