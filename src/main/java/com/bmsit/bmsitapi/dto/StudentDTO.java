package com.bmsit.bmsitapi.dto;

import com.bmsit.bmsitapi.customvalidators.EmailConstraint;
import com.bmsit.bmsitapi.customvalidators.MobileNumberConstraint;
import com.bmsit.bmsitapi.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class StudentDTO {

    private int id;

    @NotBlank(message = "name field can't be blank")
    private String studentName;

    @NotBlank(message = "register Number cam't be blank")
    @Size(min = 6, max = 12, message = "RegNo must have length between 6 to 12 characters")
    private String registerNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "provide a valid course name")
    private String course;

    @EmailConstraint
    private String email;

    @MobileNumberConstraint
    private String phoneNumber;

    @NotBlank(message = "mention the gender")
    private String gender;

    @NotBlank(message = "Address field can't be blank")
    private Address address;
}
