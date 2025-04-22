package org.example.diplomabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.diplomabackend.controller.dto.request.SubjectRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Subject(String name, List<Lesson> lessons, List<Task> tasks) {
        this.name = name;
        this.lessons = lessons;
        this.tasks = tasks;
    }

    public Subject(SubjectRequestDTO subject) {
        this.name = subject.getName();
        this.lessons = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public Subject(String name) {
        this.name = name;
    }
}