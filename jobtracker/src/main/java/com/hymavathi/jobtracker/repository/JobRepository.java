package com.hymavathi.jobtracker.repository;

import com.hymavathi.jobtracker.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findByUserId(Long userId);

    List<JobEntity> findByUserIdAndStatus(
            Long userId,
            String status
    );
}