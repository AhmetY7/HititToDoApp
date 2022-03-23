package com.hitit.todoapp.controller;

import com.hitit.todoapp.dto.JobDto;
import com.hitit.todoapp.results.DataResult;
import com.hitit.todoapp.results.SuccessDataResult;
import com.hitit.todoapp.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"","/","api/job"})
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping(value = {"","/","/list"})
    public String list(Model model){
        model.addAttribute("jobList", new SuccessDataResult<>(jobService.list(), "Jobs were listed!"));
        return "index";
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<JobDto> add(@RequestBody @Valid JobDto jobDto){
        return new SuccessDataResult<>(jobService.add(jobDto), "Job was added!");
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<JobDto> update(@RequestBody @Valid JobDto jobDto){
        return new SuccessDataResult<>(jobService.update(jobDto), "Job was updated!");
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<JobDto> delete(@RequestBody JobDto jobDto){
        return new SuccessDataResult<>(jobService.delete(jobDto), "Job was deleted!");
    }

    @PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<JobDto> find(@RequestBody JobDto jobDto){
        return new SuccessDataResult<>(jobService.find(jobDto), "Job was found!");
    }
}
