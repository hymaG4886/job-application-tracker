package com.hymavathi.jobtracker.service;

import com.hymavathi.jobtracker.dto.JobRequest;
import com.hymavathi.jobtracker.entity.JobEntity;
import com.hymavathi.jobtracker.exception.JobNotFoundException;
import com.hymavathi.jobtracker.repository.JobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public JobEntity addJob(JobRequest request) {

        JobEntity job = new JobEntity();

        job.setUserId(request.getUserId());
        job.setCompany(request.getCompany());
        job.setJobTitle(request.getJobTitle());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());

        job.setStatus(
                request.getStatus() == null
                        ? "APPLIED"
                        : request.getStatus()
        );

        job.setAppliedDate(request.getAppliedDate());
        job.setJobDescription(request.getJobDescription());

        return repository.save(job);
    }

    // GET ALL JOBS FOR USER
    public List<JobEntity> getAllJobs(Long userId) {

        return repository.findByUserId(userId);
    }

    // GET JOBS BY STATUS
    public List<JobEntity> getJobsByStatus(
            Long userId,
            String status) {

        return repository.findByUserIdAndStatus(
                userId,
                status
        );
    }

    // GET JOB BY ID
    public JobEntity getJobById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with ID: " + id
                        )
                );
    }

    // UPDATE JOB
    public JobEntity updateJob(
            Long id,
            JobRequest request) {

        JobEntity existingJob = getJobById(id);

        existingJob.setCompany(request.getCompany());
        existingJob.setJobTitle(request.getJobTitle());
        existingJob.setLocation(request.getLocation());
        existingJob.setSalary(request.getSalary());

        if (request.getStatus() != null) {
            existingJob.setStatus(request.getStatus());
        }

        existingJob.setAppliedDate(request.getAppliedDate());
        existingJob.setJobDescription(
                request.getJobDescription()
        );

        return repository.save(existingJob);
    }

    // DELETE JOB
    public void deleteJob(Long id) {

        if (!repository.existsById(id)) {

            throw new JobNotFoundException(
                    "Job not found with ID: " + id
            );
        }

        repository.deleteById(id);
    }
}