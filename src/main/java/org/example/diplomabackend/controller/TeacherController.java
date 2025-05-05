package org.example.diplomabackend.controller;

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
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return ResponseEntity.ok(
                teacherService.getAll().stream()
                        .map(TeacherResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public TeacherResponseDTO createTeacher(
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
    public TeacherResponseDTO updateTeacher(
            @RequestBody TeacherRequestDTO teacherRequestDTO,
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
    public void deleteTeacher(
            @PathVariable Long id
    ) {
        teacherService.delete(id);
    }

    @GetMapping("/teacher/{id}")
    public TeacherResponseDTO getTeacherById(
            @PathVariable Long id
    ) {
        return new TeacherResponseDTO(teacherService.getById(id));
    }
}
