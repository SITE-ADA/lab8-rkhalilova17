package az.edu.ada.wm2.courseservice.controller;

import az.edu.ada.wm2.courseservice.model.dto.CourseRequestDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseStudentsResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.EnrollmentResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.StudentCoursesResponseDto;
import az.edu.ada.wm2.courseservice.service.CourseService;
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
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "Fənlərin idarə olunması və qeydiyyat endpointləri")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Operation(summary = "Fənn yarat", description = "Yeni fənn yaradır.")
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto requestDto) {
        CourseResponseDto createdCourse = courseService.createCourse(requestDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Bütün fənləri gətir", description = "Sistemdəki bütün fənləri qaytarır.")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID-yə görə fənni gətir", description = "Verilmiş ID üzrə bir fənni qaytarır.")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Fənni yenilə", description = "Verilmiş ID üzrə fənni yeniləyir.")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto requestDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Fənni sil", description = "Verilmiş ID üzrə fənni silir.")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    @Operation(
            summary = "Tələbəni fənnə yaz",
            description = "Tələbənin mövcudluğunu və varsa ön şərt fənnini yoxlayaraq qeydiyyat yaradır."
    )
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        EnrollmentResponseDto responseDto = courseService.enrollStudent(courseId, studentId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/students")
    @Operation(
            summary = "Fənnin tələbələrini gətir",
            description = "Verilmiş fənn üzrə qeydiyyatdan keçən tələbələrin detallı məlumatlarını qaytarır."
    )
    public ResponseEntity<CourseStudentsResponseDto> getCourseStudents(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseStudents(courseId));
    }

    @GetMapping("/by-student-name")
    @Operation(
            summary = "Tələbə adına görə fənnləri gətir",
            description = "Ad və ya soyad üzrə tələbələri axtarır, sonra həmin tələbələrə aid fənnləri və qeydiyyat tarixlərini qaytarır."
    )
    public ResponseEntity<List<StudentCoursesResponseDto>> getCoursesByStudentName(
            @Parameter(description = "Tələbənin adı, soyadı və ya onların bir hissəsi", example = "Nicat")
            @RequestParam String name) {
        return ResponseEntity.ok(courseService.getCoursesByStudentName(name));
    }
}