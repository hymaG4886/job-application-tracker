package com.hymavathi.jobtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hymavathi.jobtracker.dto.InterviewRequest;
import com.hymavathi.jobtracker.entity.InterviewEntity;
import com.hymavathi.jobtracker.exception.InterviewNotFoundException;
import com.hymavathi.jobtracker.repository.InterviewRepository;

@Service
public class InterviewService {

    private final InterviewRepository repository;

    public InterviewService(
            InterviewRepository repository) {

        this.repository = repository;
    }

    public InterviewEntity addInterview(
            InterviewRequest request) {

        InterviewEntity interview =
                new InterviewEntity();

        interview.setApplicationId(
                request.getApplicationId()
        );

        interview.setInterviewDate(
                request.getInterviewDate()
        );

        interview.setInterviewRound(
                request.getInterviewRound()
        );

        interview.setNotes(
                request.getNotes()
        );

        interview.setOutcome(
                request.getOutcome()
        );

        return repository.save(interview);
    }

    public List<InterviewEntity> getInterviews(
            Long applicationId) {

        return repository.findByApplicationId(
                applicationId
        );
    }

    public InterviewEntity getInterview(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new InterviewNotFoundException(
                                "Interview not found with ID: "
                                        + id
                        )
                );
    }

    public InterviewEntity updateInterview(
            Long id,
            InterviewRequest request) {

        InterviewEntity existing =
                getInterview(id);

        existing.setInterviewDate(
                request.getInterviewDate()
        );

        existing.setInterviewRound(
                request.getInterviewRound()
        );

        existing.setNotes(
                request.getNotes()
        );

        existing.setOutcome(
                request.getOutcome()
        );

        return repository.save(existing);
    }

    public void deleteInterview(Long id) {

        if (!repository.existsById(id)) {

            throw new InterviewNotFoundException(
                    "Interview not found with ID: "
                            + id
            );
        }

        repository.deleteById(id);
    }
}