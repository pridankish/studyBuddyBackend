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
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupNumber;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Group(String groupNumber, List<User> users, University university, Schedule schedule) {
        this.groupNumber = groupNumber;
        this.users = users;
        this.university = university;
        this.schedule = schedule;
    }
}