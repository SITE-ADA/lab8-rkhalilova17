package az.edu.ada.wm2.courseservice.exception;

public class MissingPrerequisiteException extends RuntimeException {

    public MissingPrerequisiteException(Long studentId, Long courseId, Long prerequisiteCourseId) {
        super("Student " + studentId + " cannot enroll in course " + courseId
                + " before completing prerequisite course " + prerequisiteCourseId + ".");
    }
}