package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.GroupRequestDTO;

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
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToOne
    private Schedule schedule;

    public Group(String groupNumber, List<User> users, University university, Schedule schedule) {
        this.groupNumber = groupNumber;
        this.users = users;
        this.university = university;
        this.schedule = schedule;
    }

    public Group(GroupRequestDTO group) {
        this.groupNumber = group.getGroupNumber();
        this.users = group.getUsers();
        this.university = new University(group.getUniversity());
    }
}