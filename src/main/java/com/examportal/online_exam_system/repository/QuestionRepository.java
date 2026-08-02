package com.examportal.online_exam_system.repository;

import com.examportal.online_exam_system.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // Ek specific exam ki saari questions nikalne ke liye
    List<Question> findByExamId(Integer examId);
}