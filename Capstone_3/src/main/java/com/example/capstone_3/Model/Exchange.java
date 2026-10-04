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


/*    // LearningRequest 1 : 0..1 Exchange
    // Exchange is the child
    @NotNull(message = "The learning request cant be null")
    @OneToOne
    @JoinColumn(name = "request_id", unique = true)
    @JsonIgnore
    private LearningRequest learningRequest;


    // SkillOffer 1 : Many Exchange
    // Exchange is the child
    @NotNull(message = "The skill offer cant be null")
    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonIgnore
    private SkillOffer skillOffer;


    // Exchange 1 : 1 Agreement
    // Exchange is the parent
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Agreement agreement;


    // Exchange 1 : Many SessionParticipant
    // Exchange is the parent
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<SessionParticipant> sessionParticipants;


    // Exchange 1 : Many TokenTransaction
    // Exchange is the parent
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<TokenTransaction> tokenTransactions;


    // Exchange 1 : Many Review
    // Exchange is the parent
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchange")
    private Set<Review> reviews;*/
}