package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Document
public class Role {
    @Id

    private String id;
//   @DBRef
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }



}
