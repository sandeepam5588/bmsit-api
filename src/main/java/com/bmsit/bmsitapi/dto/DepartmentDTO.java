package com.bmsit.bmsitapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private int departmentId;

    @NotBlank(message = "Provide a Department name")
    private String departmentName;

    @NotBlank(message = "Provide Department code")
    private String departmentCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCommence;

    @NotBlank(message = "Provide HOD name for the department")
    private String hod;

}
