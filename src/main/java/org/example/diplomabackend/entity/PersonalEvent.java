package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.PersonalEventRequestDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personal_events")
public class PersonalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;

    private String eventType;

    private LocalDate eventDate;

    private LocalTime eventStartTime;

    private Integer eventDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PersonalEvent(String eventTitle, String eventType, LocalDate eventDate, LocalTime eventSTime, Integer eventDuration, User user) {
        this.eventTitle = eventTitle;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventStartTime = eventSTime;
        this.eventDuration = eventDuration;
        this.user = user;
    }

    public PersonalEvent(PersonalEventRequestDTO personalEventRequestDTO) {
        this.eventTitle = personalEventRequestDTO.getEventTitle();
        this.eventType = personalEventRequestDTO.getEventType();
        this.eventDate = personalEventRequestDTO.getEventDate();
        this.eventStartTime = personalEventRequestDTO.getEventStartTime();
        this.eventDuration = personalEventRequestDTO.getEventDuration();
        this.user = new User(personalEventRequestDTO.getUser());
    }
}