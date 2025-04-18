package org.example.diplomabackend.controller;

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
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping("/all")
    public ResponseEntity<List<UniversityResponseDTO>> getAllUniversities() {
        return ResponseEntity.ok(
                universityService.getAll().stream()
                        .map(UniversityResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public UniversityResponseDTO createUniversity(
            @RequestBody UniversityRequestDTO universityRequestDTO
    ) {
        var savedUniversity = universityService.addNew(
                new University(
                        universityRequestDTO.getUniversityName(),
                        universityRequestDTO.getUsers(),
                        universityRequestDTO.getGroups()
                )
        );
        return new UniversityResponseDTO(savedUniversity);
    }

    @PutMapping("/update/{id}")
    public UniversityResponseDTO updateUniversity(
            @RequestBody UniversityRequestDTO universityRequestDTO,
            @PathVariable Long id
    ) {
        var updatedUniversity = universityService.update(
                new University(
                        universityRequestDTO.getUniversityName(),
                        universityRequestDTO.getUsers(),
                        universityRequestDTO.getGroups()
                ), id);
        return new UniversityResponseDTO(updatedUniversity);
    }

    @DeleteMapping("/update/{id}")
    public void deleteUniversity(
            @PathVariable Long id
    ) {
        universityService.delete(id);
    }

    @GetMapping("/university/{id}")
    public UniversityResponseDTO getUniversityById(
            @PathVariable Long id
    ) {
        return new UniversityResponseDTO(universityService.getById(id));
    }
}
