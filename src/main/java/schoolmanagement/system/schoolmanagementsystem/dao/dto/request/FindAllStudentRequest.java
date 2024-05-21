package schoolmanagement.system.schoolmanagementsystem.dao.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FindAllStudentRequest {
    private String schoolId;
       private  int page;
    private int limit;
}
