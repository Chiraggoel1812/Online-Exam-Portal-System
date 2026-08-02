package com.examportal.online_exam_system.service;

import com.examportal.online_exam_system.dto.SubmitExamRequest;
import com.examportal.online_exam_system.entity.Exam;
import com.examportal.online_exam_system.entity.Question;
import com.examportal.online_exam_system.entity.Result;
import com.examportal.online_exam_system.entity.User;
import com.examportal.online_exam_system.repository.ExamRepository;
import com.examportal.online_exam_system.repository.QuestionRepository;
import com.examportal.online_exam_system.repository.ResultRepository;
import com.examportal.online_exam_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    // ---------- AUTOMATIC EVALUATION LOGIC ----------
    public Result submitExam(SubmitExamRequest request) {

        // 1. Exam ke saare questions nikalo
        List<Question> questions = questionRepository.findByExamId(request.getExamId());

        double score = 0;
        double marksPerQuestion = 1;
        double negativeMarks = 0.25;   // galat answer pe 0.25 marks katenge

        // 2. Har question ko check karo student ke answer se
        Map<Integer, String> studentAnswers = request.getAnswers();

        for (Question question : questions) {
            String studentAnswer = studentAnswers.get(question.getId());

            if (studentAnswer != null) {
                if (studentAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                    score += marksPerQuestion;
                } else {
                    score -= negativeMarks;
                }
            }
            // Agar answer hi nahi diya (blank chhoda), toh koi marks nahi katenge
        }

        // Score negative na ho, isliye 0 se neeche jaane se roko
        if (score < 0) {
            score = 0;
        }

        // 3. User aur Exam fetch karo
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        // 4. Result save karo
        Result result = new Result();
        result.setUser(user);
        result.setExam(exam);
        result.setScore((int) Math.round(score));
        result.setExamDate(LocalDateTime.now());

        return resultRepository.save(result);
    }
    // ---------- RESULT HISTORY (student ke saare results) ----------
    public List<Result> getResultsByUser(Integer userId) {
        return resultRepository.findByUserId(userId);
    }

    // ---------- LEADERBOARD (ek exam ke top performers) ----------
    public List<Result> getLeaderboard(Integer examId) {
        return resultRepository.findByExamIdOrderByScoreDesc(examId);
    }

    // ---------- PASS/FAIL STATUS check karne ke liye helper ----------
    public String getPassFailStatus(Result result, Exam exam) {
        double percentage = ((double) result.getScore() / exam.getTotalMarks()) * 100;
        return percentage >= 40 ? "PASS" : "FAIL";  // 40% passing criteria (aap change kar sakte ho)
    }
}