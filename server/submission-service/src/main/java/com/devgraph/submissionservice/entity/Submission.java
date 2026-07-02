package com.devgraph.submissionservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false)
    private Long userId;

    @Column(nullable=false)
    private Long problemId;

    @Lob
    @Column(nullable=false)
    private String sourceCode;

    @Column(nullable=false)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SubmissionStatus status;

    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
    }
}
