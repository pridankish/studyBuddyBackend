package org.example.diplomabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.PersonalEventRequestDTO;
import org.example.diplomabackend.controller.dto.response.PersonalEventResponseDTO;
import org.example.diplomabackend.entity.PersonalEvent;
import org.example.diplomabackend.service.PersonalEventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-events")
public class PersonalEventController {
    private final PersonalEventsService personalEventService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonalEventResponseDTO>> getAllPersonalEvents() {
        return ResponseEntity.ok(
                personalEventService.getAll().stream()
                        .map(PersonalEventResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public PersonalEventResponseDTO createPersonalEvent(
            @RequestBody PersonalEventRequestDTO personalEventRequestDTO
    ) {
        var savedPersonalEvent = personalEventService.addNew(
                new PersonalEvent(
                        personalEventRequestDTO.getEventTitle(),
                        personalEventRequestDTO.getEventType(),
                        personalEventRequestDTO.getEventDate(),
                        personalEventRequestDTO.getEventStartTime(),
                        personalEventRequestDTO.getEventDuration(),
                        personalEventRequestDTO.getUser()
                )
        );
        return new PersonalEventResponseDTO(savedPersonalEvent);
    }

    @PutMapping("/update/{id}")
    public PersonalEventResponseDTO updatePersonalEvent(
            @RequestBody PersonalEventRequestDTO personalEventRequestDTO,
            @PathVariable Long id
    ) {
        var updatedPersonalEvent = personalEventService.update(
                new PersonalEvent(
                        personalEventRequestDTO.getEventTitle(),
                        personalEventRequestDTO.getEventType(),
                        personalEventRequestDTO.getEventDate(),
                        personalEventRequestDTO.getEventStartTime(),
                        personalEventRequestDTO.getEventDuration(),
                        personalEventRequestDTO.getUser()
                ), id);
        return new PersonalEventResponseDTO(updatedPersonalEvent);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersonalEvent(
            @PathVariable Long id
    ) {
        personalEventService.delete(id);
    }

    @GetMapping("/personal-event/{id}")
    public PersonalEventResponseDTO getPersonalEvent(
        @PathVariable Long id
    ) {
        return new PersonalEventResponseDTO(personalEventService.getById(id));
    }
}
