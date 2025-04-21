package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.SubjectRequestDTO;
import org.example.diplomabackend.controller.dto.response.SubjectResponseDTO;
import org.example.diplomabackend.entity.Lesson;
import org.example.diplomabackend.entity.Subject;
import org.example.diplomabackend.entity.Task;
import org.example.diplomabackend.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        return ResponseEntity.ok(
                subjectService.getAll().stream()
                        .map(SubjectResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public SubjectResponseDTO createSubject(
            @RequestBody SubjectRequestDTO subjectRequestDTO
    ) {
        var savedSubject = subjectService.addNew(
                new Subject(
                        subjectRequestDTO.getName(),
                        subjectRequestDTO.getLessons().stream()
                                .map(Lesson::new)
                                .collect(Collectors.toList()),
                        subjectRequestDTO.getTasks().stream()
                                .map(Task::new)
                                .collect(Collectors.toList())
                )
        );
        return new SubjectResponseDTO(savedSubject);
    }

    @PutMapping("/update/{id}")
    public SubjectResponseDTO updateSubject(
            @RequestBody SubjectRequestDTO subjectRequestDTO,
            @PathVariable Long id
    ) {
        var updatedSubject = subjectService.update(
                new Subject(
                        subjectRequestDTO.getName(),
                        subjectRequestDTO.getLessons().stream()
                                .map(Lesson::new)
                                .collect(Collectors.toList()),
                        subjectRequestDTO.getTasks().stream()
                                .map(Task::new)
                                .collect(Collectors.toList())
                ), id);
        return new SubjectResponseDTO(updatedSubject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubject(
            @PathVariable Long id
    ) {
        subjectService.delete(id);
    }

    @GetMapping("/subject/{id}")
    public SubjectResponseDTO getSubjectById(
            @PathVariable Long id
    ) {
        return new SubjectResponseDTO(subjectService.getById(id));
    }
}
