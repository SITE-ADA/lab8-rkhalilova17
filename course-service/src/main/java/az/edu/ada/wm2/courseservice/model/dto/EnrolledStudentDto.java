package az.edu.ada.wm2.courseservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrolledStudentDto {

    @Schema(description = "Tələbənin ID-si", example = "15")
    private Long id;

    @Schema(description = "Tələbənin adı", example = "Nicat")
    private String firstName;

    @Schema(description = "Tələbənin soyadı", example = "Aliyev")
    private String lastName;

    @Schema(description = "Tələbənin e-poçt ünvanı", example = "nicat.aliyev@example.com")
    private String email;

    @Schema(description = "Tələbənin yaşı", example = "20")
    private Integer age;

    @Schema(description = "Fənnə qeydiyyat tarixi", example = "2026-05-20")
    private LocalDate enrollmentDate;
}