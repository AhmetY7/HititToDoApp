package com.hitit.todoapp;

import com.hitit.todoapp.dto.JobDto;
import com.hitit.todoapp.dto.StatusDto;
import com.hitit.todoapp.service.JobService;
import com.hitit.todoapp.service.StatusService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JobService jobService, StatusService statusService) {
		return args -> {
			StatusDto statusToDo = StatusDto.builder()
					.name("To Do")
					.build();
			StatusDto statusInProgress = StatusDto.builder()
					.name("In Progress")
					.build();
			StatusDto statusDone = StatusDto.builder()
					.name("Done")
					.build();
			statusService.add(statusToDo);
			statusService.add(statusInProgress);
			statusService.add(statusDone);

			JobDto firstJob = JobDto.builder()
					.description("First Job")
					.status(StatusDto.builder().id(1).build())
					.targetDate(new Date())
					.build();
			JobDto secondJob = JobDto.builder()
					.description("Second Job")
					.status(StatusDto.builder().id(2).build())
					.targetDate(new Date())
					.build();
			JobDto thirdJob = JobDto.builder()
					.description("Third Job")
					.status(StatusDto.builder().id(3).build())
					.targetDate(new Date())
					.build();

			jobService.add(firstJob);
			jobService.add(secondJob);
			jobService.add(thirdJob);

		};
	}


}
