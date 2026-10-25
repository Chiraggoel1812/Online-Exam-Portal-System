package com.examportal.online_exam_system.repository;

import com.examportal.online_exam_system.entity.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Integer> {
    Optional<ExamAttempt> findTopByUser_IdAndExam_IdOrderByStartTimeDesc(Integer userId, Integer examId);
}