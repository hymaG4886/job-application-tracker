package com.hymavathi.jobtracker.Controller;

import com.hymavathi.jobtracker.dto.JobRequest;
import com.hymavathi.jobtracker.entity.JobEntity;
import com.hymavathi.jobtracker.service.JobService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobEntity addJob(
            @Valid @RequestBody JobRequest request) {

        return service.addJob(request);
    }

    @GetMapping
    public List<JobEntity> getJobs(
            @RequestParam Long userId,
            @RequestParam(required = false) String status) {

        if (status != null) {

            return service.getJobsByStatus(
                    userId,
                    status
            );
        }

        return service.getAllJobs(userId);
    }

    @GetMapping("/{id}")
    public JobEntity getJob(
            @PathVariable Long id) {

        return service.getJobById(id);
    }

    @PutMapping("/{id}")
    public JobEntity updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request) {

        return service.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(
            @PathVariable Long id) {

        service.deleteJob(id);
    }
}