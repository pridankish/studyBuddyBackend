package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Lesson;
import org.example.diplomabackend.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SubjectRequestDTO {

    private String name;
}
