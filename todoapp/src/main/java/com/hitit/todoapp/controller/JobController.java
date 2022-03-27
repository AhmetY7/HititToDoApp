package com.hitit.todoapp.controller;

import com.hitit.todoapp.dto.JobDto;
import com.hitit.todoapp.results.SuccessDataResult;
import com.hitit.todoapp.service.JobService;
import com.hitit.todoapp.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"","/","api/job"})
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    private final StatusService statusService;

    @GetMapping(value = {"","/"})
    public String main(Model model){
        return "index";
    }

    @GetMapping(value = {"/list"})
    public String listPage(Model model) {
        model.addAttribute("jobList", new SuccessDataResult<>(jobService.list(), "Jobs were listed!"));
        return "fragments/job/list :: content";
    }

    @GetMapping(value = {"/add-page"})
    public String addPage(Model model) {
        model.addAttribute("newJob", new JobDto());
        model.addAttribute("statusList", statusService.list());
        return "fragments/job/add :: content";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("newJob") JobDto newJob){
        jobService.add(newJob);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody @Valid JobDto jobDto, Model model){
        jobService.update(jobDto);
        return listPage(model);
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestBody JobDto jobDto, Model model){
        jobService.delete(jobDto);
        return listPage(model);
    }

    @PostMapping(value = "/find")
    public String find(@RequestBody JobDto jobDto, Model model){
        model.addAttribute("job", jobService.find(jobDto));
        model.addAttribute("statusList", statusService.list());
        return "fragments/job/update :: content";
    }
}
