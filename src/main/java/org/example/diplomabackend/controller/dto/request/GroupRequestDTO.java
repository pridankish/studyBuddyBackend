package org.example.diplomabackend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequestDTO {

    private String groupNumber;

    private Long universityId;
}