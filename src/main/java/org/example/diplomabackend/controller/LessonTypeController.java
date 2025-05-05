package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.LessonTypeRequestDTO;
import org.example.diplomabackend.controller.dto.response.LessonTypeResponseDTO;
import org.example.diplomabackend.entity.LessonType;
import org.example.diplomabackend.service.LessonTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson-types")
@CrossOrigin(origins = "*")
public class LessonTypeController {
    private final LessonTypeService lessonTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<LessonTypeResponseDTO>> getAllLessonTypes() {
        return ResponseEntity.ok(
                lessonTypeService.getAll().stream()
                        .map(LessonTypeResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public LessonTypeResponseDTO createLessonType(
            @RequestBody LessonTypeRequestDTO requestDTO
    ) {
        var savedLessonType = lessonTypeService.addNew(
                new LessonType(
                        requestDTO.getLessonTypeName()
                )
        );
        return new LessonTypeResponseDTO(savedLessonType);
    }

    @PutMapping("/update/{id}")
    public LessonTypeResponseDTO updateLessonType(
            @RequestBody LessonTypeRequestDTO lessonTypeRequestDTO,
            @PathVariable Long id
    ) {
        var updatedLessonType = lessonTypeService.update(
                new LessonType(
                        lessonTypeRequestDTO.getLessonTypeName()
                ), id
        );
        return new LessonTypeResponseDTO(updatedLessonType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLessonType(
            @PathVariable Long id
    ) {
        lessonTypeService.delete(id);
    }

    @GetMapping("lesson-type/{id}")
    public LessonTypeResponseDTO getLessonTypeById(
            @PathVariable Long id
    ) {
        return new LessonTypeResponseDTO(lessonTypeService.getById(id));
    }
}
