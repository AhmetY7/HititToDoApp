package com.hitit.todoapp.service;

import com.hitit.todoapp.dto.JobDto;
import com.hitit.todoapp.dto.StatusDto;

import java.util.List;

public interface StatusService {
    StatusDto add(StatusDto statusDto);
    StatusDto delete(StatusDto statusDto);
    StatusDto update(StatusDto statusDto);
    List<StatusDto> list();
    StatusDto find(StatusDto statusDto);
}
