package com.bmsit.bmsitapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private int courseId;

    @NotEmpty(message = "Provide a course name")
    private String courseName;

    @NotEmpty(message = "Provide a course code")
    private String code;

    @NotEmpty(message = "Provide the course duration")
    @Min(value = 1)
    @Max(value = 3)
    private int durationInYears;

    @NotEmpty(message = "Provide a department name")
    private String department;
}
