package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDtoIn {
    @NotNull(message = "The rating can't be null")
    @Min(value = 1, message = "The rating must be at least 1")
    @Max(value = 5, message = "The rating can't exceed 5")
    private Integer rating;

    private String comment;

    @NotNull(message = "The reviewed account ID can't be null")
    private Integer reviewedAccountId;
}
