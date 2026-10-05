package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileDtoOut {

    private Integer accountId;
    private String email;
    private String accountType;
    private Integer tokenBalance;
    private String status;
    private Boolean emailVerified;
    private LocalDateTime createdAt;
    private String name;
    private String description;
    private String phone;
    private String city;
    private String logo;
    private Boolean verified;
}
