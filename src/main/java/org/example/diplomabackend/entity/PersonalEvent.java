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
public class PersonalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;

    private String eventType;

    private LocalDate eventDate;

    private LocalTime eventSTime;

    private Integer eventDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PersonalEvent(String eventTitle, String eventType, LocalDate eventDate, LocalTime eventSTime, Integer eventDuration, User user) {
        this.eventTitle = eventTitle;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventSTime = eventSTime;
        this.eventDuration = eventDuration;
        this.user = user;
    }
}