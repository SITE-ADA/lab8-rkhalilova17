package az.edu.ada.wm2.courseservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCoursesResponseDto {

    @Schema(description = "Tələbənin ID-si", example = "15")
    private Long studentId;

    @Schema(description = "Tələbənin adı", example = "Nicat")
    private String firstName;

    @Schema(description = "Tələbənin soyadı", example = "Aliyev")
    private String lastName;

    @Schema(description = "Axtarış nəticəsində tapılan tələbəyə aid fənlər")
    private List<StudentCourseDto> courses;
}