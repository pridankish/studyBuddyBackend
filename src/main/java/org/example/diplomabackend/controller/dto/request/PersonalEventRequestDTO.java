package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PersonalEventRequestDTO {

    private String eventTitle;

    private String eventType;

    private LocalDate eventDate;

    private LocalTime eventStartTime;

    private Integer eventDuration;

    private UserRequestDTO user;
}