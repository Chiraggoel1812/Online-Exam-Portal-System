package com.examportal.online_exam_system.dto;

import lombok.Data;
import java.util.Map;

@Data
public class SubmitExamRequest {
    private Integer userId;
    private Integer examId;
    private Map<Integer, String> answers;
    // Key = questionId, Value = selected option ("A"/"B"/"C"/"D")
}