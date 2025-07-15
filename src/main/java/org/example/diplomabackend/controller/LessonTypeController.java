package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Типы учебных занятий",
        description = "Методы для работы с типами учебных занятий"
)
public class LessonTypeController {
    private final LessonTypeService lessonTypeService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех типов учебных занятий")
    public ResponseEntity<List<LessonTypeResponseDTO>> getAllLessonTypes() {
        return ResponseEntity.ok(
                lessonTypeService.getAll().stream()
                        .map(LessonTypeResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать тип учебного занятия")
    public LessonTypeResponseDTO createLessonType(
            @Parameter(description = "Информация о типе учебного занятия")
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
    @Operation(summary = "Изменить тип учебного занятия по id")
    public LessonTypeResponseDTO updateLessonType(
            @Parameter(description = "Информация о типе учебного занятия")
            @RequestBody LessonTypeRequestDTO lessonTypeRequestDTO,

            @Parameter(description = "id типа учебного занятия по id")
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
    @Operation(summary = "Удалить тип учебного занятия по id")
    public void deleteLessonType(
            @Parameter(description = "id типа учебного занятия")
            @PathVariable Long id
    ) {
        lessonTypeService.delete(id);
    }

    @GetMapping("lesson-type/{id}")
    @Operation(description = "Получить информацию о типе учебного занятия по его id")
    public LessonTypeResponseDTO getLessonTypeById(
            @Parameter(description = "id типа учебного занятия")
            @PathVariable Long id
    ) {
        return new LessonTypeResponseDTO(lessonTypeService.getById(id));
    }
}
