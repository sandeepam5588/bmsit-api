package com.bmsit.bmsitapi.dto;

import com.bmsit.bmsitapi.model.ResultDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private int resultId;
    @NotBlank(message = "Register number can't be empty")
    private String registerNumber;

    @NotBlank(message = "provide total marks secured")
    @Min(0)
    @Max(1000)
    private int marksSecured;

    @NotBlank(message = "result status can't be empty")
    private String resultStatus;

    @NotBlank(message = "Result details cannot be empty")
    private ResultDetail resultDetail;
}
