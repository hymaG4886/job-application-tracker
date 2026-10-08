package com.hymavathi.jobtracker.repository;

import com.hymavathi.jobtracker.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository
        extends JpaRepository<InterviewEntity, Long> {

    List<InterviewEntity> findByApplicationId(Long applicationId);
}