package schoolmanagement.system.schoolmanagementsystem.exception;

public class SchoolDoesExistException extends RuntimeException{
    public SchoolDoesExistException(String message) {
        super(message);
    }

    public static String SchoolDoesExistException(String id) {
        return "School with " + id  + " Does not exist";

    }
}
