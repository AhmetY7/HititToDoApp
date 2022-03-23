package com.hitit.todoapp.service.Impl;

import com.hitit.todoapp.dao.JobDao;
import com.hitit.todoapp.dto.JobDto;
import com.hitit.todoapp.entity.JobEntity;
import com.hitit.todoapp.service.JobService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {

    private final String mapperId = "Job";
    private final DozerBeanMapper dozer;
    private final JobDao jobDao;

    @Autowired
    public JobServiceImpl(DozerBeanMapper dozer, JobDao jobDao) {
        this.dozer = dozer;
        this.jobDao = jobDao;
    }

    @Override
    @Transactional
    public JobDto add(JobDto jobDto) {
        JobEntity jobEntity = dozer.map(jobDto, JobEntity.class, mapperId);
        jobEntity.setInsertedAt(new Date());
        jobEntity.setUpdatedAt(new Date());
        return dozer.map(jobDao.save(jobEntity), JobDto.class, mapperId);
    }

    @Override
    @Transactional
    public JobDto delete(JobDto jobDto) {
        if (jobDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<JobEntity> optionalJobEntity = jobDao.findById(jobDto.getId());
        if (optionalJobEntity.isPresent()) {
            JobEntity jobEntity = optionalJobEntity.get();
            jobEntity.setUpdatedAt(new Date());
            jobEntity.setIsDeleted(true);
            return dozer.map(jobDao.save(jobEntity), JobDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + jobDto.getId());
    }

    @Override
    @Transactional
    public JobDto update(JobDto jobDto) {
        if (jobDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<JobEntity> optionalJobEntity = jobDao.findById(jobDto.getId());
        if (optionalJobEntity.isPresent()) {
            JobEntity jobEntity = dozer.map(jobDto, JobEntity.class, mapperId);
            jobEntity.setUpdatedAt(new Date());
            return dozer.map(jobDao.save(jobEntity), JobDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + jobDto.getId());

    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDto> list() {
        List<JobEntity> jobEntities = jobDao.findAll();
        List<JobDto> jobDtos = new LinkedList<>();
        for (JobEntity jobEntity : jobEntities) {
            jobDtos.add(dozer.map(jobEntity, JobDto.class, mapperId));
        }
        return jobDtos;
    }

    @Override
    public JobDto find(JobDto jobDto) {
        if (jobDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<JobEntity> optionalJobEntity = jobDao.findById(jobDto.getId());
        if (optionalJobEntity.isPresent()) {
            return dozer.map(optionalJobEntity.get(), JobDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + jobDto.getId());
    }
}
