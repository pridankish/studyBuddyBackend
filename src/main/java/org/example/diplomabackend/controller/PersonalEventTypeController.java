package org.example.diplomabackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Типы внеучебных мероприятий",
        description = "Методы для работы с типами внеучебных мероприятий"
)
public class PersonalEventTypeController {
    private final PersonalEventTypeService personalEventTypeService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех типов внеучебных мероприятий")
    public ResponseEntity<List<PersonalEventTypeResponseDTO>> getAllPersonalEventTypes() {
        return ResponseEntity.ok(
                personalEventTypeService.getAll().stream()
                        .map(PersonalEventTypeResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Создать тип внеучебного мероприятия")
    public PersonalEventTypeResponseDTO createPersonalEventType(
            @Parameter(description = "Информация о типе внеучебного мероприятия")
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
    @Operation(summary = "Изменить тип внеучебного мероприятия по его id")
    public PersonalEventTypeResponseDTO updatePersonalEventType(
            @Parameter(description = "Информация о типе внеучебного мероприятия")
            @RequestBody PersonalEventTypeRequestDTO requestDTO,

            @Parameter(description = "id типа внеучебного мероприятия")
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
    @Operation(summary = "Удалить тип внецчебного мероприятия по его id")
    public void deletePersonalEventType(
            @Parameter(description = "id типа внеучебного мероприятия")
            @PathVariable Long id
    ) {
        personalEventTypeService.delete(id);
    }

    @GetMapping("/personal-event-type/{id}")
    public PersonalEventTypeResponseDTO getPersonalEventTypeById(
            @Parameter(description = "id типа внеучебного мероприятия")
            @PathVariable Long id
    ) {
        return new PersonalEventTypeResponseDTO(personalEventTypeService.getById(id));
    }
}
