package com.hitit.todoapp.service.Impl;

import com.hitit.todoapp.dao.StatusDao;
import com.hitit.todoapp.dto.StatusDto;
import com.hitit.todoapp.entity.StatusEntity;
import com.hitit.todoapp.service.StatusService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StatusServiceImpl implements StatusService {

    private final String mapperId = "Status";
    private final DozerBeanMapper dozer;
    private final StatusDao statusDao;

    @Autowired
    public StatusServiceImpl(DozerBeanMapper dozer, StatusDao statusDao) {
        this.dozer = dozer;
        this.statusDao = statusDao;
    }
    @Override
    @Transactional
    public StatusDto add(StatusDto statusDto) {
        StatusEntity statusEntity = dozer.map(statusDto, StatusEntity.class, mapperId);
        statusEntity.setInsertedAt(new Date());
        statusEntity.setUpdatedAt(new Date());
        StatusEntity statusEntity1 = statusDao.save(statusEntity);
        return dozer.map(statusEntity1, StatusDto.class, mapperId);
    }

    @Override
    @Transactional
    public StatusDto delete(StatusDto statusDto) {
        if (statusDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<StatusEntity> optionalStatusEntity = statusDao.findById(statusDto.getId());
        if (optionalStatusEntity.isPresent()) {
            StatusEntity statusEntity = optionalStatusEntity.get();
            statusEntity.setUpdatedAt(new Date());
            statusEntity.setIsDeleted(true);
            return dozer.map(statusDao.save(statusEntity), StatusDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + statusDto.getId());
    }

    @Override
    @Transactional
    public StatusDto update(StatusDto statusDto) {
        if (statusDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<StatusEntity> optionalStatusEntity = statusDao.findById(statusDto.getId());
        if (optionalStatusEntity.isPresent()) {
            StatusEntity statusEntity = dozer.map(statusDto, StatusEntity.class, mapperId);
            statusEntity.setUpdatedAt(new Date());
            return dozer.map(statusDao.save(statusEntity), StatusDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + statusDto.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusDto> list() {
        List<StatusEntity> statusEntities = statusDao.findAll();
        List<StatusDto> statusDtos = new LinkedList<>();
        for (StatusEntity statusEntity : statusEntities) {
            statusDtos.add(dozer.map(statusEntity, StatusDto.class, mapperId));
        }
        return statusDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public StatusDto find(StatusDto statusDto) {
        if (statusDto.getId() == null)
            throw new NoSuchElementException("ID can not be null!");
        Optional<StatusEntity> optionalStatusEntity = statusDao.findById(statusDto.getId());
        if (optionalStatusEntity.isPresent()) {
            return dozer.map(optionalStatusEntity.get(), StatusDto.class, mapperId);
        }
        throw new NoSuchElementException("Record not found with ID " + statusDto.getId());
    }
}
