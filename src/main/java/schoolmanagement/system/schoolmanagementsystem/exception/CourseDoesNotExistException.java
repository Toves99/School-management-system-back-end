package schoolmanagement.system.schoolmanagementsystem.exception;

public class CourseDoesNotExistException extends RuntimeException {
    public CourseDoesNotExistException(String message) {
        super(message);
    }

    public static String CourseDoesNotExist(String id) {
        return "Course id " + id  + " does not exist";
    }
    public static String CourseDoesNotExist(String courseName, String id) {
        return "Course name: " + courseName + " does not exist";
    }
}
