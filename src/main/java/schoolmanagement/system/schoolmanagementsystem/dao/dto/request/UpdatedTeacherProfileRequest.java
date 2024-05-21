package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UpdatedTeacherProfileRequest {
    private String schoolId;
    private String teacherId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;

    private String email;

}
