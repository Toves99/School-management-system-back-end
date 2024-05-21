package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Document("School")
@NoArgsConstructor
public class School {
    @Id
    private String id;
    private String schoolName;
    private String schoolLocation;
    private String email;
    @DBRef
    private Set<Student> students = new HashSet<>();

    @DBRef
    private Set<Course> courses = new HashSet<>();

    @DBRef
    private List<Teacher> teachers = new ArrayList<>();

    @DBRef
    private Set<Role> roleHashSet;

    public School(String id, String schoolName, String schoolLocation, String email,

                  RoleType roleType) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
        this.email = email;
        if (roleHashSet == null) {
            roleHashSet = new HashSet<>();
            roleHashSet.add(new Role(roleType));


        }

    }
}
