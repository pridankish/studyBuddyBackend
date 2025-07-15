package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.SubjectRequestDTO;
import org.example.diplomabackend.controller.dto.response.SubjectResponseDTO;
import org.example.diplomabackend.entity.Subject;
import org.example.diplomabackend.service.SubjectService;
import org.example.diplomabackend.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
@CrossOrigin(origins = "*")
@Tag(
        name = "Дисцилпины",
        description = "Методы для работы с дисциплинами"
)
public class SubjectController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех дисциплин")
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        return ResponseEntity.ok(
                subjectService.getAll().stream()
                        .map(SubjectResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать дисциплину")
    public SubjectResponseDTO createSubject(
            @Parameter(description = "Информация о дисциплине")
            @RequestBody SubjectRequestDTO subjectRequestDTO
    ) {
        var savedSubject = subjectService.addNew(
                new Subject(
                        subjectRequestDTO.getName(),
                        teacherService.getById(subjectRequestDTO.getTeacherId())
                )
        );
        return new SubjectResponseDTO(savedSubject);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить дисциплину по ее id")
    public SubjectResponseDTO updateSubject(
            @Parameter(description = "Информация о дисциплине")
            @RequestBody SubjectRequestDTO subjectRequestDTO,

            @Parameter(description = "id дисциплины")
            @PathVariable Long id
    ) {
        var updatedSubject = subjectService.update(
                new Subject(
                        subjectRequestDTO.getName(),
                        teacherService.getById(subjectRequestDTO.getTeacherId())
                ), id);
        return new SubjectResponseDTO(updatedSubject);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить дисциплину по ее id")
    public void deleteSubject(
            @Parameter(description = "id дисциплины")
            @PathVariable Long id
    ) {
        subjectService.delete(id);
    }

    @GetMapping("/subject/{id}")
    @Operation(summary = "Получить дисциплину по ее id")
    public SubjectResponseDTO getSubjectById(
            @Parameter(description = "id дисциплины")
            @PathVariable Long id
    ) {
        return new SubjectResponseDTO(subjectService.getById(id));
    }
}
