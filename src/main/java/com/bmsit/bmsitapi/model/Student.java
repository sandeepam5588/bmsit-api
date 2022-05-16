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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String studentName;
    private String RegisterNumber;
    private LocalDate dateOfBirth;
    private String course;
    private String Department;
    private String email;
    private String phoneNumber;
    private Address address;
    private String gender;
}
