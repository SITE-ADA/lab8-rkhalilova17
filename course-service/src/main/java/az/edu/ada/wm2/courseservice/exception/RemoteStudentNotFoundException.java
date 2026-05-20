package az.edu.ada.wm2.courseservice.exception;

public class RemoteStudentNotFoundException extends RuntimeException {

    public RemoteStudentNotFoundException(Long studentId) {
        super("Student with id " + studentId + " was not found in student-service.");
    }
}