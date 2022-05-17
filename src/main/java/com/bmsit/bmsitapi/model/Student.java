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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String studentName;
    private String RegisterNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String course;

    //many students belong to one department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String email;
    private String phoneNumber;
    private String gender;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Address> address;
}
