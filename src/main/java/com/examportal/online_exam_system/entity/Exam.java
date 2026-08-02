package com.examportal.online_exam_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exam_name", nullable = false)
    private String examName;

    @Column(nullable = false)
    private Integer duration;   // in minutes

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;
}