package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.LessonRequestDTO;
import org.example.diplomabackend.controller.dto.response.LessonResponseDTO;
import org.example.diplomabackend.entity.Lesson;
import org.example.diplomabackend.service.GroupService;
import org.example.diplomabackend.service.LessonService;
import org.example.diplomabackend.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<List<LessonResponseDTO>> getAllLessons() {
        return ResponseEntity.ok(
                lessonService.getAll().stream()
                        .map(LessonResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public LessonResponseDTO createLesson(
            @RequestBody LessonRequestDTO lessonRequestDTO
    ) {
        var savedLesson = lessonService.addNew(
                new Lesson(
                        lessonRequestDTO.getLessonDate(),
                        groupService.getById(lessonRequestDTO.getGroupId()),
                        subjectService.getById(lessonRequestDTO.getSubjectId()),
                        lessonRequestDTO.getAuditoryNumber(),
                        lessonRequestDTO.getLessonType(),
                        lessonRequestDTO.getStartTime(),
                        lessonRequestDTO.getEndTime()
                )
        );
        return new LessonResponseDTO(savedLesson);
    }

    @PutMapping("/update/{id}")
    public LessonResponseDTO updateLesson(
            @RequestBody LessonRequestDTO lessonRequestDTO,
            @PathVariable Long id
    ) {
        var updatedLesson = lessonService.update(
                new Lesson(
                        lessonRequestDTO.getLessonDate(),
                        groupService.getById(lessonRequestDTO.getGroupId()),
                        subjectService.getById(lessonRequestDTO.getSubjectId()),
                        lessonRequestDTO.getAuditoryNumber(),
                        lessonRequestDTO.getLessonType(),
                        lessonRequestDTO.getStartTime(),
                        lessonRequestDTO.getEndTime()
                ), id);
        return new LessonResponseDTO(updatedLesson);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLesson(
            @PathVariable Long id
    ) {
        lessonService.delete(id);
    }

    @GetMapping("/lesson/{id}")
    public LessonResponseDTO getLessonById(
            @PathVariable Long id
    ) {
        return new LessonResponseDTO(lessonService.getById(id));
    }
}
