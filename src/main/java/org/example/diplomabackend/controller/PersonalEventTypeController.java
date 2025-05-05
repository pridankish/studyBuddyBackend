package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.PersonalEventTypeRequestDTO;
import org.example.diplomabackend.controller.dto.response.PersonalEventTypeResponseDTO;
import org.example.diplomabackend.entity.PersonalEventType;
import org.example.diplomabackend.service.PersonalEventTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-event-types")
@CrossOrigin(origins = "*")
public class PersonalEventTypeController {
    private final PersonalEventTypeService personalEventTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonalEventTypeResponseDTO>> getAllPersonalEventTypes() {
        return ResponseEntity.ok(
                personalEventTypeService.getAll().stream()
                        .map(PersonalEventTypeResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public PersonalEventTypeResponseDTO createPersonalEventType(
            @RequestBody PersonalEventTypeRequestDTO requestDTO
    ) {
        var savedPersonalEventType = personalEventTypeService.addNew(
                new PersonalEventType(
                        requestDTO.getPersonalEventTypeName()
                )
        );
        return new PersonalEventTypeResponseDTO(savedPersonalEventType);
    }

    @PutMapping("/update/{id}")
    public PersonalEventTypeResponseDTO updatePersonalEventType(
            @RequestBody PersonalEventTypeRequestDTO requestDTO,
            @PathVariable Long id
    ) {
        var updatedPersonalEvent = personalEventTypeService.update(
                new PersonalEventType(
                        requestDTO.getPersonalEventTypeName()
                ), id
        );

        return new PersonalEventTypeResponseDTO(updatedPersonalEvent);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersonalEventType(
            @PathVariable Long id
    ) {
        personalEventTypeService.delete(id);
    }

    @GetMapping("/personal-event-type/{id}")
    public PersonalEventTypeResponseDTO getPersonalEventTypeById(
            @PathVariable Long id
    ) {
        return new PersonalEventTypeResponseDTO(personalEventTypeService.getById(id));
    }
}
