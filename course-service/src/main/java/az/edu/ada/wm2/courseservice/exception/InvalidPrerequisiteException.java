package az.edu.ada.wm2.courseservice.exception;

public class InvalidPrerequisiteException extends RuntimeException {

    public InvalidPrerequisiteException(Long prerequisiteCourseId) {
        super("Prerequisite course with id " + prerequisiteCourseId + " does not exist.");
    }

    public InvalidPrerequisiteException() {
        super("A course cannot be its own prerequisite.");
    }
}