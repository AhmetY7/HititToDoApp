package com.hitit.todoapp.dao;

import com.hitit.todoapp.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDao extends JpaRepository<JobEntity, Long> {
}
