package az.edu.ada.wm2.courseservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDateTime enrollmentDate;

    @PrePersist
    public void PrePersist() {
        this.enrollmentDate = LocalDateTime.now();
    }
}
