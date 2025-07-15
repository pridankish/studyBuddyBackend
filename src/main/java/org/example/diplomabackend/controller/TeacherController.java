package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.TeacherRequestDTO;
import org.example.diplomabackend.controller.dto.response.TeacherResponseDTO;
import org.example.diplomabackend.entity.Teacher;
import org.example.diplomabackend.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
@CrossOrigin(origins = "*")
@Tag(
        name = "Преподаватели",
        description = "Методы для работы с преподавателями"
)
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    @Operation(summary = "Получить список преподавателей")
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return ResponseEntity.ok(
                teacherService.getAll().stream()
                        .map(TeacherResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать преподавателя")
    public TeacherResponseDTO createTeacher(
            @Parameter(description = "Информация о преподавателе")
            @RequestBody TeacherRequestDTO teacherRequestDTO
    ) {
        var savedTeacher = teacherService.addNew(
                new Teacher(
                        teacherRequestDTO.getFirstName(),
                        teacherRequestDTO.getMiddleName(),
                        teacherRequestDTO.getLastName()
                )
        );
        return new TeacherResponseDTO(savedTeacher);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить преподавателя по его id")
    public TeacherResponseDTO updateTeacher(
            @Parameter(description = "Информация о преподавателе")
            @RequestBody TeacherRequestDTO teacherRequestDTO,

            @Parameter(description = "id преподавателя")
            @PathVariable Long id
    ) {
        var updatedTeacher = teacherService.update(
                new Teacher(
                        teacherRequestDTO.getFirstName(),
                        teacherRequestDTO.getMiddleName(),
                        teacherRequestDTO.getLastName()
                ), id
        );
        return new TeacherResponseDTO(updatedTeacher);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить преподавателя по его id")
    public void deleteTeacher(
            @Parameter(description = "id преподавателя")
            @PathVariable Long id
    ) {
        teacherService.delete(id);
    }

    @GetMapping("/teacher/{id}")
    @Operation(summary = "Получить преподавателя по его id")
    public TeacherResponseDTO getTeacherById(
            @Parameter(description = "id преподавателя")
            @PathVariable Long id
    ) {
        return new TeacherResponseDTO(teacherService.getById(id));
    }
}
