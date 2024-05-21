package schoolmanagement.system.schoolmanagementsystem.exception;

public class TeacherDoesNotExistException extends RuntimeException {
    public TeacherDoesNotExistException(String message) {
        super(message);
    }

    public static String TeacherDoesNotExist(String id) {

        return "Teacher id " + id  + " does not exist";
    }
    public static String TeacherDoesNotExist(String teachersName, String id) {
        return "Teacher name: " + teachersName + " does not exist";
    }
}
