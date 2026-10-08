package com.hymavathi.jobtracker.Controller;

import com.hymavathi.jobtracker.dto.InterviewRequest;
import com.hymavathi.jobtracker.entity.InterviewEntity;
import com.hymavathi.jobtracker.service.InterviewService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService service;

    public InterviewController(
            InterviewService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterviewEntity addInterview(
            @Valid @RequestBody InterviewRequest request) {

        return service.addInterview(request);
    }

    @GetMapping
    public List<InterviewEntity> getInterviews(
            @RequestParam Long applicationId) {

        return service.getInterviews(
                applicationId
        );
    }

    @GetMapping("/{id}")
    public InterviewEntity getInterview(
            @PathVariable Long id) {

        return service.getInterview(id);
    }

    @PutMapping("/{id}")
    public InterviewEntity updateInterview(
            @PathVariable Long id,
            @Valid @RequestBody InterviewRequest request) {

        return service.updateInterview(
                id,
                request
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInterview(
            @PathVariable Long id) {

        service.deleteInterview(id);
    }
}