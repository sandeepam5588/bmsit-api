package com.bmsit.bmsitapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Student student;
    private LocalDate dateOfAnnouncement;
    private String academicYear;
    private int semester;
    private String course;
    private String department;
    private int totalMarks;
    private String resultStatus;
}
