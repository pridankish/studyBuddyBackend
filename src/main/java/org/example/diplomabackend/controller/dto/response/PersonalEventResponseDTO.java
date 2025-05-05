package org.example.diplomabackend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.PersonalEvent;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class PersonalEventResponseDTO {
    private Long id;

    private String eventTitle;

    private Long eventTypeId;

    private LocalDate eventDate;

    private LocalTime eventStartTime;

    private Integer eventDuration;

    private Long userId;

    public PersonalEventResponseDTO(PersonalEvent personalEvent) {
        this.id = personalEvent.getId();
        this.eventTitle = personalEvent.getEventTitle();
        this.eventTypeId = personalEvent.getPersonalEventType().getId();
        this.eventDate = personalEvent.getEventDate();
        this.eventStartTime = personalEvent.getEventStartTime();
        this.eventDuration = personalEvent.getEventDuration();
        this.userId = personalEvent.getUser().getId();
    }
}
