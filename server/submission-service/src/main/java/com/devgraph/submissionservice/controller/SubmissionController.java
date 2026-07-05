package com.devgraph.submissionservice.controller;

import com.devgraph.submissionservice.entity.Submission;
import com.devgraph.submissionservice.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionRepository submissionRepository;
    private final com.devgraph.submissionservice.service.SubmissionPublisher submissionPublisher;

    // 1. Submit a new solution
    @PostMapping
    public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
        // By default, a new submission starts as PENDING
        submission.setStatus(com.devgraph.submissionservice.entity.SubmissionStatus.PENDING);
        
        Submission saved = submissionRepository.save(submission);
        
        // PUBLISH TO REDIS
        submissionPublisher.sendToExecutionQueue(saved.getId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 2. Get submission status and results
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long id) {
        return submissionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Get all submissions for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Submission>> getUserSubmissions(@PathVariable Long userId) {
        return ResponseEntity.ok(submissionRepository.findByUserId(userId));
    }
}
