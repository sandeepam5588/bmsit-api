package com.bmsit.bmsitapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String facultyName;

    private String facultyRegNumber;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String qualification;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfResignation;

    private String mobileNumber;

    private String gender;

    private String designation;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Address> address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
