package com.hitit.todoapp.service;

import com.hitit.todoapp.dto.JobDto;

import java.util.List;

public interface JobService {
    JobDto add(JobDto jobDto);
    JobDto delete(JobDto jobDto);
    JobDto update(JobDto jobDto);
    List<JobDto> list();
    JobDto find(JobDto jobDto);
}
