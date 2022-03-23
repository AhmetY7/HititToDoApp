package com.hitit.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
    private Integer id;

    @NotEmpty(message = "Name can not be null!")
    private String name;
}
