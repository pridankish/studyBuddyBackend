package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PersonalEvent> personalEvents;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public User(String firstName, String lastName, String email, String password, LocalDateTime createdAt, Group group, List<PersonalEvent> personalEvents, University university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.group = group;
        this.personalEvents = personalEvents;
        this.university = university;
    }
}