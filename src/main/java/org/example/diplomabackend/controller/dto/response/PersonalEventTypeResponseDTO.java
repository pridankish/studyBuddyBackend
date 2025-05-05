package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.PersonalEventType;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class PersonalEventTypeResponseDTO {
    private Long id;

    private String name;

    private List<PersonalEventResponseDTO> personalEvents;

    public PersonalEventTypeResponseDTO(PersonalEventType personalEventType) {
        this.id = personalEventType.getId();
        this.name = personalEventType.getName();
        this.personalEvents = personalEventType.getPersonalEvents().stream()
                .map(PersonalEventResponseDTO::new)
                .collect(Collectors.toList());
    }
}
