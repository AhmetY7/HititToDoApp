package com.hitit.todoapp.controller;

import com.hitit.todoapp.dto.StatusDto;
import com.hitit.todoapp.results.DataResult;
import com.hitit.todoapp.results.SuccessDataResult;
import com.hitit.todoapp.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/status")
@AllArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<List<StatusDto>> list(){
        return new SuccessDataResult<>(statusService.list(), "Jobs were listed!");
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<StatusDto> add(@RequestBody @Valid StatusDto statusDto){
        return new SuccessDataResult<>(statusService.add(statusDto), "Job was added!");
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<StatusDto> update(@RequestBody @Valid StatusDto statusDto){
        return new SuccessDataResult<>(statusService.update(statusDto), "Job was updated!");
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<StatusDto> delete(@RequestBody StatusDto statusDto){
        return new SuccessDataResult<>(statusService.delete(statusDto), "Job was deleted!");
    }

    @PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult<StatusDto> find(@RequestBody StatusDto statusDto){
        return new SuccessDataResult<>(statusService.find(statusDto), "Job was found!");
    }
}

