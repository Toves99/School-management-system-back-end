package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("student")
public class Student {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String gender;
    private String studentAge;
    private String email;
    private String password;
    @DBRef
    private Set<Course> courses = new HashSet<>();
    @DBRef
    private Set<Role> roleHashSet;


    public Student(String id, String studentFirstName, String studentLastName,String gender, String studentAge,String email,
                   String password,
                   RoleType roleType) {
        this.id = id;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.gender = gender;
        this.studentAge = studentAge;
        this.email = email;
        this.password = password;
        if (roleHashSet == null) {
            roleHashSet = new HashSet<>();
            roleHashSet.add(new Role(roleType));


        }
    }
}
