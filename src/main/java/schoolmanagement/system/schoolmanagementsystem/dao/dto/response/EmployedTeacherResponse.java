package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployedTeacherResponse {
    private String message;
    private String schoolId;
    private String teacherId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
}
