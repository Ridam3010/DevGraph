package com.devgraph.problemservice.config;

import com.devgraph.problemservice.entity.Difficulty;
import com.devgraph.problemservice.entity.Problem;
import com.devgraph.problemservice.entity.TestCase;
import com.devgraph.problemservice.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProblemRepository problemRepository;

    @Override
    public void run(String... args) throws Exception {
        if (problemRepository.count() == 0) {
            
            // Problem 1: Two Sum
            Problem twoSum = Problem.builder()
                    .title("Two Sum")
                    .description("Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.")
                    .difficulty(Difficulty.EASY)
                    .timeLimit(2000)
                    .memoryLimit(256)
                    .build();

            TestCase tc1_1 = TestCase.builder()
                    .input("[2,7,11,15]\n9")
                    .expectedOutput("[0,1]")
                    .isSample(true)
                    .problem(twoSum)
                    .build();

            TestCase tc1_2 = TestCase.builder()
                    .input("[3,2,4]\n6")
                    .expectedOutput("[1,2]")
                    .isSample(true)
                    .problem(twoSum)
                    .build();
            twoSum.setTestCases(Arrays.asList(tc1_1, tc1_2));

            // Problem 2: Reverse String
            Problem reverseString = Problem.builder()
                    .title("Reverse String")
                    .description("Write a function that reverses a string. The input string is given as an array of characters s.")
                    .difficulty(Difficulty.EASY)
                    .timeLimit(1000)
                    .memoryLimit(256)
                    .build();

            TestCase tc2_1 = TestCase.builder()
                    .input("[\"h\",\"e\",\"l\",\"l\",\"o\"]")
                    .expectedOutput("[\"o\",\"l\",\"l\",\"e\",\"h\"]")
                    .isSample(true)
                    .problem(reverseString)
                    .build();
            reverseString.setTestCases(Arrays.asList(tc2_1));

            // Problem 3: Longest Substring Without Repeating Characters
            Problem longestSub = Problem.builder()
                    .title("Longest Substring Without Repeating Characters")
                    .description("Given a string s, find the length of the longest substring without repeating characters.")
                    .difficulty(Difficulty.MEDIUM)
                    .timeLimit(3000)
                    .memoryLimit(512)
                    .build();
            
            TestCase tc3_1 = TestCase.builder()
                    .input("\"abcabcbb\"")
                    .expectedOutput("3")
                    .isSample(true)
                    .problem(longestSub)
                    .build();
            longestSub.setTestCases(Arrays.asList(tc3_1));
            
            // Problem 4: Merge k Sorted Lists
            Problem mergeKLists = Problem.builder()
                    .title("Merge k Sorted Lists")
                    .description("You are given an array of k linked-lists lists, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.")
                    .difficulty(Difficulty.HARD)
                    .timeLimit(5000)
                    .memoryLimit(512)
                    .build();
                    
            TestCase tc4_1 = TestCase.builder()
                    .input("[[1,4,5],[1,3,4],[2,6]]")
                    .expectedOutput("[1,1,2,3,4,4,5,6]")
                    .isSample(true)
                    .problem(mergeKLists)
                    .build();
            mergeKLists.setTestCases(Arrays.asList(tc4_1));

            // Problem 5: Valid Parentheses
            Problem validParentheses = Problem.builder()
                    .title("Valid Parentheses")
                    .description("Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.")
                    .difficulty(Difficulty.EASY)
                    .timeLimit(1500)
                    .memoryLimit(128)
                    .build();
            
            TestCase tc5_1 = TestCase.builder()
                    .input("\"()[]{}\"")
                    .expectedOutput("true")
                    .isSample(true)
                    .problem(validParentheses)
                    .build();
            validParentheses.setTestCases(Arrays.asList(tc5_1));

            problemRepository.saveAll(Arrays.asList(twoSum, reverseString, longestSub, mergeKLists, validParentheses));

            System.out.println("✅ Database seeded with 5 sample problems!");
        }
    }
}
