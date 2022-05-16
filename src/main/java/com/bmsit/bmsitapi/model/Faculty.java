package com.bmsit.bmsitapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String facultyName;
    private String facultyRegNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String qualification;
    private LocalDate dateOfJoin;
    private LocalDate dateOfResignation;
    private String mobileNumber;
    private String gender;

    @Embedded
    private Address address;
    private String department;
}
