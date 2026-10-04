package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^(BEGINNER|INTERMEDIATE|ADVANCED|EXPERT)$",
            message = "Level must be BEGINNER, INTERMEDIATE, ADVANCED or EXPERT")
    @Check(constraints = "level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')")
    @Column(columnDefinition = "varchar(20) not null default 'BEGINNER'")
    private String level = "BEGINNER";

    @Column(columnDefinition = "boolean not null default false")
    private Boolean verified = false;


    @ManyToOne
    @JoinColumn(name = "skill_id",referencedColumnName = "id")
    @JsonIgnore
    private Skill skill;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "accountSkill")
    private Set<SkillAssessment> skillAssessments;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;









}
