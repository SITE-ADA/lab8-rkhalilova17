package az.edu.ada.wm2.studentservice.controller;

import az.edu.ada.wm2.studentservice.model.dto.StudentRequestDto;
import az.edu.ada.wm2.studentservice.model.dto.StudentResponseDto;
import az.edu.ada.wm2.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Tələbələrin idarə olunması endpointləri")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Tələbə yarat", description = "Yeni tələbə qeydiyyatı yaradır.")
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto requestDto) {
        StudentResponseDto createdStudent = studentService.createStudent(requestDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Bütün tələbələri gətir", description = "Sistemdəki bütün tələbələri qaytarır.")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID-yə görə tələbəni gətir", description = "Verilmiş ID üzrə bir tələbəni qaytarır.")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Ada görə tələbə axtar", description = "Ad və ya soyad üzrə tələbələri axtarır.")
    public ResponseEntity<List<StudentResponseDto>> searchStudentsByName(
            @Parameter(description = "Tələbənin adı, soyadı və ya onların bir hissəsi", example = "Ali")
            @RequestParam String name) {
        return ResponseEntity.ok(studentService.searchStudentsByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Tələbəni yenilə", description = "Verilmiş ID üzrə tələbə məlumatlarını yeniləyir.")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto requestDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Tələbəni sil", description = "Verilmiş ID üzrə tələbəni silir.")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}