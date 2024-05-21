package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateStudentProfileResponse {
    private String firstName;
    private String message;
    private String id;
}
