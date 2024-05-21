package schoolmanagement.system.schoolmanagementsystem.dao.data.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document
public class Attendance {
        @Id
        private Teacher id;

        @DBRef
        private Student student;

        private LocalDate date;

        private AttendanceStatus status;
}
