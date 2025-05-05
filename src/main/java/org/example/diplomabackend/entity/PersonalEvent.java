package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalDate eventDate;

    private LocalTime eventStartTime;

    private Integer eventDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "personal_event_type_id")
    private PersonalEventType personalEventType;

    public PersonalEvent(String eventTitle, PersonalEventType personalEventType, LocalDate eventDate, LocalTime eventSTime, Integer eventDuration, User user) {
        this.eventTitle = eventTitle;
        this.personalEventType = personalEventType;
        this.eventDate = eventDate;
        this.eventStartTime = eventSTime;
        this.eventDuration = eventDuration;
        this.user = user;
    }
}