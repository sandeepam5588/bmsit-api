package com.bmsit.bmsitapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String departmentName;
    private String departmentCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCommence;
    private String hod;

    // One department can have one or more faculty
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Faculty> faculties;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Student> students;

    //private List<Course> courses;
}
