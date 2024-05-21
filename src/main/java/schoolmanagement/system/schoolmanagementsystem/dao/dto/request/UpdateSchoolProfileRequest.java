package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateSchoolProfileRequest {
    private String schoolId;
    private String schoolName;
    private String schoolLocation;
    private String email;
}
