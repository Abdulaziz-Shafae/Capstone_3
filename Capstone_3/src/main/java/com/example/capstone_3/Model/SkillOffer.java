package com.example.capstone_3.Model;

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
public class SkillOffer {

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


    @NotNull(message = "Token cost is required")
    @Positive(message = "Token cost must be positive")
    @Column(columnDefinition = "int not null")
    private Integer tokenCost;


    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(columnDefinition = "int not null default 1")
    private Integer capacity = 1;


    @Pattern(regexp = "^(ACTIVE|PAUSED|CANCELLED)$",
            message = "Status must be ACTIVE, PAUSED or CANCELLED")
    @Check(constraints = "status IN ('ACTIVE', 'PAUSED', 'CANCELLED')")
    @Column(columnDefinition = "varchar(20) not null default 'ACTIVE'")
    private String status = "ACTIVE";


    @Column(columnDefinition = "datetime not null")
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "provider_account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account providerAccount;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @JsonIgnore
    private Skill skill;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillOffer")
    private Set<Exchange> exchanges;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillOffer")
    private Set<Session> sessions;

    @OneToMany(mappedBy = "skillOffer")
    @JsonIgnore
    private Set<LearningRequest> learningRequests;
}
