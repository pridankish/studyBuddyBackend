package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PersonalEventRequestDTO {

    private String eventTitle;

    private Long eventTypeId;

    private LocalDate eventDate;

    private LocalTime eventStartTime;

    private Integer eventDuration;

    private Long userId;
}