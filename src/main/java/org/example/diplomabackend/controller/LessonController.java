package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.LessonRequestDTO;
import org.example.diplomabackend.controller.dto.response.LessonResponseDTO;
import org.example.diplomabackend.entity.Lesson;
import org.example.diplomabackend.service.GroupService;
import org.example.diplomabackend.service.LessonService;
import org.example.diplomabackend.service.LessonTypeService;
import org.example.diplomabackend.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
@CrossOrigin(origins = "*")
@Tag(
        name = "Учебные занятия",
        description = "Методы для работы с учебными занятиями"
)
public class LessonController {
    private final LessonService lessonService;
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final LessonTypeService lessonTypeService;

    @GetMapping("/all")
    @Operation(summary = "Получить все учебные занятия")
    public ResponseEntity<List<LessonResponseDTO>> getAllLessons() {
        return ResponseEntity.ok(
                lessonService.getAll().stream()
                        .map(LessonResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать учебное занятие")
    public LessonResponseDTO createLesson(
            @Parameter(description = "Информация об учебном занятии")
            @RequestBody LessonRequestDTO lessonRequestDTO
    ) {
        var savedLesson = lessonService.addNew(
                new Lesson(
                        lessonRequestDTO.getLessonDate(),
                        groupService.getById(lessonRequestDTO.getGroupId()),
                        subjectService.getById(lessonRequestDTO.getSubjectId()),
                        lessonRequestDTO.getAuditoryNumber(),
                        lessonTypeService.getById(lessonRequestDTO.getLessonTypeId()),
                        lessonRequestDTO.getStartTime(),
                        lessonRequestDTO.getEndTime()
                )
        );
        return new LessonResponseDTO(savedLesson);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить учебного занятия по её id")
    public LessonResponseDTO updateLesson(
            @Parameter(description = "Информация об учебном занятии")
            @RequestBody LessonRequestDTO lessonRequestDTO,

            @Parameter(description = "id учебного занятия")
            @PathVariable Long id
    ) {
        var updatedLesson = lessonService.update(
                new Lesson(
                        lessonRequestDTO.getLessonDate(),
                        groupService.getById(lessonRequestDTO.getGroupId()),
                        subjectService.getById(lessonRequestDTO.getSubjectId()),
                        lessonRequestDTO.getAuditoryNumber(),
                        lessonTypeService.getById(lessonRequestDTO.getLessonTypeId()),
                        lessonRequestDTO.getStartTime(),
                        lessonRequestDTO.getEndTime()
                ), id);
        return new LessonResponseDTO(updatedLesson);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить учебного занятия по ее id")
    public void deleteLesson(
            @Parameter(description = "id учебного занятия")
            @PathVariable Long id
    ) {
        lessonService.delete(id);
    }

    @GetMapping("/lesson/{id}")
    @Operation(summary = "Получить информацию об учебном занятии по ее id")
    public LessonResponseDTO getLessonById(
            @Parameter(description = "id учебного занятия")
            @PathVariable Long id
    ) {
        return new LessonResponseDTO(lessonService.getById(id));
    }
}
