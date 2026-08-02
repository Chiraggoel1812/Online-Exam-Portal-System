package com.examportal.online_exam_system.repository;

import com.examportal.online_exam_system.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

    // Ek student ke saare results (result history) nikalne ke liye
    List<Result> findByUserId(Integer userId);

    // Leaderboard ke liye - ek exam ke saare results, score ke hisaab se sort
    List<Result> findByExamIdOrderByScoreDesc(Integer examId);
}