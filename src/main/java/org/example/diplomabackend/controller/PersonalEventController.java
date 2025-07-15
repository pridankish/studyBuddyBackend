package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.diplomabackend.controller.dto.request.PersonalEventRequestDTO;
import org.example.diplomabackend.controller.dto.response.PersonalEventResponseDTO;
import org.example.diplomabackend.entity.PersonalEvent;
import org.example.diplomabackend.service.PersonalEventTypeService;
import org.example.diplomabackend.service.PersonalEventsService;
import org.example.diplomabackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-events")
@CrossOrigin(origins = "*")
@Tag(
        name = "Внеучебные мероприятия",
        description = "Методы для работы с внеучебными мероприятиями"
)
public class PersonalEventController {
    private final PersonalEventsService personalEventService;
    private final UserService userService;
    private final PersonalEventTypeService personalEventTypeService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех внеучебных мероприятий")
    public ResponseEntity<List<PersonalEventResponseDTO>> getAllPersonalEvents() {
        return ResponseEntity.ok(
                personalEventService.getAll().stream()
                        .map(PersonalEventResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать внеучебное мероприятие")
    public PersonalEventResponseDTO createPersonalEvent(
            @Parameter(description = "Информация о внеучебном мероприятии")
            @RequestBody PersonalEventRequestDTO personalEventRequestDTO
    ) {
        var savedPersonalEvent = personalEventService.addNew(
                new PersonalEvent(
                        personalEventRequestDTO.getEventTitle(),
                        personalEventTypeService.getById(personalEventRequestDTO.getEventTypeId()),
                        personalEventRequestDTO.getEventDate(),
                        personalEventRequestDTO.getEventStartTime(),
                        personalEventRequestDTO.getEventDuration(),
                        userService.getById(personalEventRequestDTO.getUserId())
                )
        );
        return new PersonalEventResponseDTO(savedPersonalEvent);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Изменить внеучебное мероприятие по его id")
    public PersonalEventResponseDTO updatePersonalEvent(
            @Parameter(description = "Информация о внеучебном мероприятии")
            @RequestBody PersonalEventRequestDTO personalEventRequestDTO,

            @Parameter(description = "id внеучебного мероприятия")
            @PathVariable Long id
    ) {
        var updatedPersonalEvent = personalEventService.update(
                new PersonalEvent(
                        personalEventRequestDTO.getEventTitle(),
                        personalEventTypeService.getById(personalEventRequestDTO.getEventTypeId()),
                        personalEventRequestDTO.getEventDate(),
                        personalEventRequestDTO.getEventStartTime(),
                        personalEventRequestDTO.getEventDuration(),
                        userService.getById(personalEventRequestDTO.getUserId())
                ), id);
        return new PersonalEventResponseDTO(updatedPersonalEvent);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить внеучебное мероприятие по его id")
    public void deletePersonalEvent(
            @Parameter(description = "id внеучебного мероприятия")
            @PathVariable Long id
    ) {
        personalEventService.delete(id);
    }

    @GetMapping("/personal-event/{id}")
    @Operation(summary = "Получить информацию о группе по ее id")
    public PersonalEventResponseDTO getPersonalEvent(
            @Parameter(description = "id внеучебного мероприятия")
            @PathVariable Long id
    ) {
        return new PersonalEventResponseDTO(personalEventService.getById(id));
    }
}
