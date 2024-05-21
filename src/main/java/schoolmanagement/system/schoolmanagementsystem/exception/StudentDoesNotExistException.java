package schoolmanagement.system.schoolmanagementsystem.exception;

public class StudentDoesNotExistException extends RuntimeException{
    public StudentDoesNotExistException(String message) {
        super(message);
    }

    public static String StudentDoestNotExist(String id) {
        return "Student with " + id  + "Does not exist";

    }

    public static String StudentDoestNotExist(String id, String email) {
        return "Student with " + email  + "Does not exist";

    }

    public static String StudentDoesNotExist(String name) {
        return "Student with " + name + "Does not exist";

    }
}
