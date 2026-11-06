package com.examportal.online_exam_system.service;

import com.examportal.online_exam_system.entity.Exam;
import com.examportal.online_exam_system.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    // Naya exam create karo (Admin)
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    // Saare exams dikhao (Student list dekhne ke liye)
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Ek specific exam nikalo ID se
    public Optional<Exam> getExamById(Integer id) {
        return examRepository.findById(id);
    }

    // Exam update karo
    public Exam updateExam(Integer id, Exam updatedExam) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        exam.setExamName(updatedExam.getExamName());
        exam.setDuration(updatedExam.getDuration());
        exam.setTotalMarks(updatedExam.getTotalMarks());

        return examRepository.save(exam);
    }

    // Exam delete karo
    public void deleteExam(Integer id) {
        examRepository.deleteById(id);
    }
}