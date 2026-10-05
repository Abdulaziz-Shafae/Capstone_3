package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class LearningRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(max = 500, message = "Description must be at most 500 characters")
    @Column(columnDefinition = "varchar(500)")
    private String description;


    @NotEmpty(message = "Mode is required")
    @Pattern(regexp = "^(ONLINE|IN_PERSON|BOTH)$",
            message = "Mode must be ONLINE, IN_PERSON or BOTH")
    @Check(constraints = "mode IN ('ONLINE', 'IN_PERSON', 'BOTH')")
    @Column(columnDefinition = "varchar(20) not null")
    private String mode;

    @Column(columnDefinition = "boolean not null default false")
    private Boolean urgent = false;


    @Column(columnDefinition = "boolean not null default false")
    private Boolean weekend = false;



    @NotNull(message = "Base tokens is required")
    @Positive(message = "Base tokens must be positive")
    @Column(columnDefinition = "int not null")
    private Integer baseTokens;


    @Column(columnDefinition = "int not null default 0")
    private Integer urgentTokens = 0;


    @Column(columnDefinition = "int not null default 0")
    private Integer weekendTokens = 0;

    @Future(message = "Needed by must be a future date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(columnDefinition = "datetime")
    private LocalDateTime neededBy;

    @Pattern(regexp = "^(OPEN|MATCHED|CLOSED|CANCELLED)$",
            message = "Status must be OPEN, MATCHED, CLOSED or CANCELLED")
    @Check(constraints = "status IN ('OPEN', 'MATCHED', 'CLOSED', 'CANCELLED')")
    @Column(columnDefinition = "varchar(20) not null default 'OPEN'")
    private String status = "OPEN";


    @Column(columnDefinition = "datetime not null")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "requester_account_id",referencedColumnName = "id")
    @JsonIgnore
    private Account requesterAccount;


    @ManyToOne
    @JoinColumn(name ="skill_id" ,referencedColumnName = "id" )
    @JsonIgnore
    private Skill skill;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "learningRequest")
    private Exchange exchange;

}
