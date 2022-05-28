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

    private String facultyId;

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

    @ElementCollection
    @Embedded
    private List<Address> address;
}

/**
 * With the @Valid annotation we ensure that the data fits the validation rules.
 * @PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_VALUE,
 *         produces = MediaType.APPLICATION_JSON_VALUE)
 * public City createCity(@RequestBody @Valid City city) {
 */