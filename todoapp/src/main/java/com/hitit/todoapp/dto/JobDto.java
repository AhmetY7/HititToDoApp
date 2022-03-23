package com.hitit.todoapp.dto;

import com.hitit.todoapp.entity.StatusEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private Long id;

    @NotEmpty(message = "Description can not be null!")
    private String description;

    @NotNull(message = "Status can not be null!")
    private StatusDto status;

    @NotNull(message = "Date can not be null!")
    private Date targetDate;
}
