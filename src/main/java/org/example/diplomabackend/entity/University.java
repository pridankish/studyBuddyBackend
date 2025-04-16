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
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String universityName;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    public University(String universityName, List<User> users, List<Group> groups) {
        this.universityName = universityName;
        this.users = users;
        this.groups = groups;
    }
}