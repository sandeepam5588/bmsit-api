package com.bmsit.bmsitapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDetailDTO {
    private int resultDetailId;
    @NotBlank(message = "Date of Announcement can't be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAnnouncement;

    @NotBlank(message = "academiv year can't be empty")
    @Min(1946)
    @Max(2022)
    private int academicYear;
    @NotBlank(message = "semester can't be empty")
    @Min(1)
    @Max(8)
    private int semester;

    @NotBlank(message = "Course name can't be empty")
    private String course;

    @NotBlank(message = "Department name can't be empty")
    private String department;

    @NotBlank(message = "totalmarks can't be empty")
    @Min(500)
    @Max(1000)
    private int totalMarks;
}
