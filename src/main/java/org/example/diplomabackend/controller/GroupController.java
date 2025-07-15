package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.GroupRequestDTO;
import org.example.diplomabackend.controller.dto.response.GroupResponseDTO;
import org.example.diplomabackend.entity.Group;
import org.example.diplomabackend.service.GroupService;

import org.example.diplomabackend.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
@CrossOrigin(origins = "*")
@Tag(
        name = "Группы",
        description = "Методы для работы с группами"
)
public class GroupController {
    private final GroupService groupService;
    private final UniversityService universityService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех групп")
    public ResponseEntity<List<GroupResponseDTO>> getAllGroups() {
        return ResponseEntity.ok(
                groupService.getAll().stream()
                        .map(GroupResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать группу")
    public GroupResponseDTO createGroup(
            @Parameter(description = "Информация о группе")
            @RequestBody GroupRequestDTO groupRequestDTO
    ) {
        var savedGroup = groupService.addNew(
                new Group (
                        groupRequestDTO.getGroupNumber(),
                        universityService.getById(groupRequestDTO.getUniversityId())
                )
        );
        return new GroupResponseDTO(savedGroup);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить группу по ее id")
    public GroupResponseDTO updateGroup(
            @Parameter(description = "Информация о группе")
            @RequestBody GroupRequestDTO groupRequestDTO,

            @Parameter(description = "id группы")
            @PathVariable Long id
    ) {
        var updatedGroup = groupService.update(
                new Group (
                        groupRequestDTO.getGroupNumber(),
                        universityService.getById(groupRequestDTO.getUniversityId())
                ), id);

        return new GroupResponseDTO(updatedGroup);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить группу по ее id")
    public void deleteGroup(
            @Parameter(description = "id группы")
            @PathVariable Long id
    ) {
        groupService.delete(id);
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "Получить информацию о группе по ее id")
    public GroupResponseDTO getGroupById(
            @Parameter(description = "id группы")
            @PathVariable Long id
    ) {
        return new GroupResponseDTO(groupService.getById(id));
    }
}
