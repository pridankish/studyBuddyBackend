package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.diplomabackend.entity.Group;
import org.example.diplomabackend.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UniversityRequestDTO {

    private String universityName;
}
