package com.examportal.online_exam_system.controller;

import com.examportal.online_exam_system.entity.Exam;
import com.examportal.online_exam_system.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    private ExamService examService;

    // POST /api/exams  -> naya exam banao (Admin)
    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    // GET /api/exams  -> saare exams dikhao (Student)
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    // GET /api/exams/{id}  -> ek specific exam dikhao
    @GetMapping("/{id}")
    public Optional<Exam> getExamById(@PathVariable Integer id) {
        return examService.getExamById(id);
    }

    // PUT /api/exams/{id}  -> exam update karo
    @PutMapping("/{id}")
    public Exam updateExam(@PathVariable Integer id, @RequestBody Exam exam) {
        return examService.updateExam(id, exam);
    }

    // DELETE /api/exams/{id}  -> exam delete karo
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Integer id) {
        examService.deleteExam(id);
        return "Exam deleted successfully!";
    }
}