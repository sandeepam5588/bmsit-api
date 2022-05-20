package com.bmsit.bmsitapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class ResultDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resultDetailId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAnnouncement;
    private String academicYear;
    private int semester;
    private String course;
    private String department;
    private int totalMarks;
}
