package com.devgraph.submissionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionPublisher {

    private final StringRedisTemplate redisTemplate;
    
    private static final String SUBMISSION_QUEUE = "code_submission_queue";

    public void sendToExecutionQueue(Long submissionId) {
        redisTemplate.convertAndSend(SUBMISSION_QUEUE, String.valueOf(submissionId));
        System.out.println("🚀 Sent submission ID " + submissionId + " to Redis queue!");
    }
}
