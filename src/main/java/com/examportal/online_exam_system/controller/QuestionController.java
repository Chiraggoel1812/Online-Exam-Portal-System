package com.examportal.online_exam_system.controller;

import com.examportal.online_exam_system.dto.QuestionResponseDTO;
import com.examportal.online_exam_system.entity.Exam;
import com.examportal.online_exam_system.entity.Question;
import com.examportal.online_exam_system.service.ExamService;
import com.examportal.online_exam_system.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    // POST /api/questions/{examId}  -> naya question add karo (Admin)
    @PostMapping("/{examId}")
    public Question addQuestion(@PathVariable Integer examId, @RequestBody Question question) {
        Exam exam = examService.getExamById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        question.setExam(exam);
        return questionService.addQuestion(question);
    }

    // GET /api/questions/exam/{examId}  -> ek exam ke saare questions (without correctAnswer for students)
    @GetMapping("/exam/{examId}")
    public List<QuestionResponseDTO> getQuestionsByExam(@PathVariable Integer examId) {
        return questionService.getQuestionsByExamId(examId);
    }

    // PUT /api/questions/{id}  -> question edit karo
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    // DELETE /api/questions/{id}  -> question delete karo
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return "Question deleted successfully!";
    }
}