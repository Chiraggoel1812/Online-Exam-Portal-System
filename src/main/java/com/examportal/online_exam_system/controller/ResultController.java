package com.examportal.online_exam_system.controller;

import com.examportal.online_exam_system.dto.SubmitExamRequest;
import com.examportal.online_exam_system.entity.Result;
import com.examportal.online_exam_system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // POST /api/results/submit  -> exam submit karo, auto evaluate ho
    @PostMapping("/submit")
    public Result submitExam(@RequestBody SubmitExamRequest request) {
        return resultService.submitExam(request);
    }

    // GET /api/results/user/{userId}  -> student ke saare results (history)
    @GetMapping("/user/{userId}")
    public List<Result> getResultsByUser(@PathVariable Integer userId) {
        return resultService.getResultsByUser(userId);
    }

    // GET /api/results/leaderboard/{examId}  -> ek exam ka leaderboard
    @GetMapping("/leaderboard/{examId}")
    public List<Result> getLeaderboard(@PathVariable Integer examId) {
        return resultService.getLeaderboard(examId);
    }
}