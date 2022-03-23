package com.hitit.todoapp.dao;

import com.hitit.todoapp.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDao extends JpaRepository<StatusEntity, Integer> {
}
