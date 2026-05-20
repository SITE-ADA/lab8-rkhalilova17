package az.edu.ada.wm2.courseservice.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {
        super("Course with id " + id + " was not found.");
    }
}