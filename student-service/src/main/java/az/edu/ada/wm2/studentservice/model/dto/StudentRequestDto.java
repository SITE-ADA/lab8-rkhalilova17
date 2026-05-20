package az.edu.ada.wm2.studentservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {

    @Schema(description = "Tələbənin adı", example = "Nicat")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "Tələbənin soyadı", example = "Aliyev")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(description = "Tələbənin e-poçt ünvanı", example = "nicat.aliyev@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @Schema(description = "Tələbənin yaşı", example = "20", minimum = "16")
    @Min(value = 16, message = "Age must be at least 16")
    private Integer age;
}