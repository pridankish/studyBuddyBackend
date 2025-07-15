package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.UniversityRequestDTO;
import org.example.diplomabackend.controller.dto.response.UniversityResponseDTO;
import org.example.diplomabackend.entity.University;
import org.example.diplomabackend.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/universities")
@CrossOrigin(origins = "*")
@Tag(
        name = "Университеты",
        description = "Методы для работы с университетами"
)
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping("/all")
    @Operation(summary = "Получить список университетов")
    public ResponseEntity<List<UniversityResponseDTO>> getAllUniversities() {
        return ResponseEntity.ok(
                universityService.getAll().stream()
                        .map(UniversityResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать университет")
    public UniversityResponseDTO createUniversity(
            @Parameter(description = "Информация об университете")
            @RequestBody UniversityRequestDTO universityRequestDTO
    ) {
        var savedUniversity = universityService.addNew(
                new University(
                        universityRequestDTO.getUniversityName()
                )
        );
        return new UniversityResponseDTO(savedUniversity);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить университет по id")
    public UniversityResponseDTO updateUniversity(
            @Parameter(description = "Информация об университете")
            @RequestBody UniversityRequestDTO universityRequestDTO,

            @Parameter(description = "id университета")
            @PathVariable Long id
    ) {
        var updatedUniversity = universityService.update(
                new University(
                        universityRequestDTO.getUniversityName()
                ), id);
        return new UniversityResponseDTO(updatedUniversity);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить университет по id")
    public void deleteUniversity(
            @Parameter(description = "id университета")
            @PathVariable Long id
    ) {
        universityService.delete(id);
    }

    @GetMapping("/university/{id}")
    @Operation(summary = "Получить университет по id")
    public UniversityResponseDTO getUniversityById(
            @Parameter(description = "id университета")
            @PathVariable Long id
    ) {
        return new UniversityResponseDTO(universityService.getById(id));
    }
}
