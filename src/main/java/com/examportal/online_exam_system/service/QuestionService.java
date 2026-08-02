package com.examportal.online_exam_system.service;

import com.examportal.online_exam_system.entity.Question;
import com.examportal.online_exam_system.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Naya question add karo (Admin)
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Ek exam ke saare questions nikalo (Student exam start karega tab)
    public List<Question> getQuestionsByExamId(Integer examId) {
        return questionRepository.findByExamId(examId);
    }

    // Question edit karo
    public Question updateQuestion(Integer id, Question updatedQuestion) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(updatedQuestion.getQuestionText());
        question.setOptionA(updatedQuestion.getOptionA());
        question.setOptionB(updatedQuestion.getOptionB());
        question.setOptionC(updatedQuestion.getOptionC());
        question.setOptionD(updatedQuestion.getOptionD());
        question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        return questionRepository.save(question);
    }

    // Question delete karo
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}