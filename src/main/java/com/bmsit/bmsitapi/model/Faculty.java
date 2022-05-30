package com.bmsit.bmsitapi.model;

import com.bmsit.bmsitapi.customvalidators.EmailConstraint;
import com.bmsit.bmsitapi.customvalidators.MobileNumberConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Faculty name can't be blank")
    private String facultyName;

    @NotBlank(message = "Faculty id can't be blank")
    private String facultyId;

    @NotBlank
    @EmailConstraint
    private String email;

    @Past(message = "Date of birth should be in past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "provide a date for qualification")
    private String qualification;

    @PastOrPresent(message = "Please provide past or present date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfResignation;

    @NotBlank
    @MobileNumberConstraint
    private String mobileNumber;

    private String gender;

    private String designation;

    @ElementCollection
    @Embedded
    private List<Address> address;
}