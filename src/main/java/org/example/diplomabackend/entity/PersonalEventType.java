package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personal_event_type")
public class PersonalEventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "personalEventType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PersonalEvent> personalEvents = new ArrayList<>();

    public PersonalEventType(String name) {
        this.name = name;
    }
}