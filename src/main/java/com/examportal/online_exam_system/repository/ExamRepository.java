package com.examportal.online_exam_system.repository;

import com.examportal.online_exam_system.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    // Abhi ke liye extra methods ki zaroorat nahi
    // save(), findAll(), findById(), deleteById() already mil jayenge
}