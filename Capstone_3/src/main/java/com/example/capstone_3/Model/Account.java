package com.example.capstone_3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "The email cant be blank")
    @NotEmpty(message = "The email cant be empty")
    @Email(message = "Email must be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    @Column(columnDefinition = "VARCHAR(150) not null unique")
    private String email;


    @NotBlank(message = "The password cant be blank")
    @NotEmpty(message = "The password cant be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    @Column(columnDefinition = "VARCHAR(255) not null")
    private String password;


    @NotBlank(message = "The account type cant be blank")
    @Pattern(
            regexp = "^(INDIVIDUAL|COMPANY|ADMIN)$",
            message = "Account type must be INDIVIDUAL, COMPANY, or ADMIN"
    )
    @Check(constraints = "account_type IN ('INDIVIDUAL', 'COMPANY', 'ADMIN')")
    @Column(columnDefinition = "VARCHAR(10) not null")
    private String accountType;


    @NotNull(message = "The token balance cant be null")
    @Min(value = 0, message = "Token balance cant be negative")
    @Column(columnDefinition = "INT DEFAULT 3")
    private Integer tokenBalance = 3;


    @NotBlank(message = "The status cant be blank")
    @Pattern(
            regexp = "^(ACTIVE|SUSPENDED|BLOCKED)$",
            message = "Status must be ACTIVE, SUSPENDED, or BLOCKED"
    )
    @Check(constraints = "status IN ('ACTIVE', 'SUSPENDED', 'BLOCKED')")
    @Column(columnDefinition = "VARCHAR(9) not null")
    private String status = "ACTIVE";


    @NotNull(message = "Email verified cant be null")
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean emailVerified = false;


    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt = LocalDateTime.now();


//    // Account 1 : 1 IndividualProfile
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
//    private IndividualProfile individualProfile;


//    // Account 1 : 1 CompanyProfile
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
//    private CompanyProfile companyProfile;


    // Account 1 : Many AccountSkill
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<AccountSkill> accountSkills; //سويتها


    // Account 1 : Many LearningRequest
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requesterAccount")
    private Set<LearningRequest> learningRequests;// سويتها


    // Account 1 : Many SkillOffer
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "providerAccount")
    private Set<SkillOffer> skillOffers; // سويتها


//    // Account 1 : Many TokenTransaction
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
//    private Set<TokenTransaction> tokenTransactions;
//
//
//    // Reviews written by this account
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewerAccount")
//    private Set<Review> reviewsWritten;
//
//
//    // Reviews received by this account
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedAccount")
//    private Set<Review> reviewsReceived;
}
