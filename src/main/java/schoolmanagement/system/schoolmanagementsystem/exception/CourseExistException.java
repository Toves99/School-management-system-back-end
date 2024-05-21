package schoolmanagement.system.schoolmanagementsystem.exception;

import lombok.Data;

@Data
public class CourseExistException extends RuntimeException {
    public CourseExistException(String message){

    }
}
