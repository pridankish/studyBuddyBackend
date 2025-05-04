package org.example.diplomabackend.controller;

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
public class GroupController {
    private final GroupService groupService;
    private final UniversityService universityService;

    @GetMapping("/all")
    public ResponseEntity<List<GroupResponseDTO>> getAllGroups() {
        return ResponseEntity.ok(
                groupService.getAll().stream()
                        .map(GroupResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public GroupResponseDTO createGroup(
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
    public GroupResponseDTO updateGroup(
        @RequestBody GroupRequestDTO groupRequestDTO,
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
    public void deleteGroup(
            @PathVariable Long id
    ) {
        groupService.delete(id);
    }

    @GetMapping("/group/{id}")
    public GroupResponseDTO getGroupById(
            @PathVariable Long id
    ) {
        return new GroupResponseDTO(groupService.getById(id));
    }
}
