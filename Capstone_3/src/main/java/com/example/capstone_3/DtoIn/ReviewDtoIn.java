package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDtoIn {

    @NotNull(message = "The rating can't be null")
    @Min(value = 1, message = "The rating must be at least 1")
    @Max(value = 5, message = "The rating can't exceed 5")
    private Integer rating;

    private String comment;

    @NotNull(message = "The exchange ID can't be null")
    private Integer exchangeId;

    @NotNull(message = "The reviewer account ID can't be null")
    private Integer reviewerAccountId;

    @NotNull(message = "The reviewed account ID can't be null")
    private Integer reviewedAccountId;
}

