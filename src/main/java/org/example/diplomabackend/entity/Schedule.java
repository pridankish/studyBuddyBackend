package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.ScheduleRequestDTO;

import java.util.ArrayList;
import java.util.List;

//@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    public Schedule(Group group) {
        this.group = group;
        this.lessons = new ArrayList<>();
    }
//
//    public Schedule(ScheduleRequestDTO schedule) {
//        this.group = new Group(schedule.getGroup());
//        this.lessons = new ArrayList<>();
//    }
}