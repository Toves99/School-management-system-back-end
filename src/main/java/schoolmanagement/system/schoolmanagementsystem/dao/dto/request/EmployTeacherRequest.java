package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployTeacherRequest {

    private String schoolId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;

}
