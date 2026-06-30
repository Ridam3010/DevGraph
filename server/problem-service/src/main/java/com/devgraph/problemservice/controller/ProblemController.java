package com.devgraph.problemservice.controller;

import com.devgraph.problemservice.entity.Problem;
import com.devgraph.problemservice.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemRepository problemRepository;

    // List all problems (no pagination for brevity)
    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        return ResponseEntity.ok(problemRepository.findAll());
    }

    // Get a single problem with its test cases
    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblem(@PathVariable Long id) {
        return problemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new problem (including nested test cases)
    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        if (problem.getTestCases() != null) {
            problem.getTestCases().forEach(tc -> tc.setProblem(problem));
        }
        // Because of CascadeType.ALL on Problem.testCases,
        // JPA will persist both the problem and its test cases.
        Problem saved = problemRepository.save(problem);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
