package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


/*
    @NotNull(message = "The token amount cant be null")
*/
    @Column(columnDefinition = "INT not null")
    private Integer tokenAmount;


/*    @NotNull(message = "The status cant be null")
    @NotEmpty(message = "The status cant be empty")
    @NotBlank(message = "The status cant be blank")
    @Pattern(
            regexp = "^(PENDING|ACCEPTED|IN_PROGRESS|COMPLETED|CANCELLED|DISPUTED)$",
            message = "Status must be PENDING, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED, or DISPUTED"
    )*/
    @Check(constraints = "status IN ('PENDING', 'ACCEPTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED', 'DISPUTED')")
    @Column(columnDefinition = "VARCHAR(20) not null DEFAULT 'PENDING'")
    private String status = "PENDING";


/*
    @NotNull(message = "The created at cant be null")
*/
    @Column(columnDefinition = "DATETIME not null")
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column(columnDefinition = "DATETIME")
    private LocalDateTime completedAt;




    @NotNull(message = "The learning request cant be null")
    @OneToOne
    @JoinColumn(name = "request_id", unique = true)
    @JsonIgnore
    private LearningRequest learningRequest; //سويتها

    @NotNull(message = "The skill offer cant be null")
    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonIgnore
    private SkillOffer skillOffer;//سويتها

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Agreement agreement;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<SessionParticipant> sessionParticipants;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<TokenTransaction> tokenTransactions;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<Review> reviews;
}