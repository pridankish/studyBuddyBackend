package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Schedule;
import org.example.diplomabackend.entity.University;
import org.example.diplomabackend.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupRequestDTO {

    private String groupNumber;

    private List<User> users = new ArrayList<>();

    private University university;

    private Schedule schedule;
}