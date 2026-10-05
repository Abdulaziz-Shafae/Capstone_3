package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"exchange_id", "reviewer_account_id"})
})
@Check(constraints = "rating >= 1 AND rating <= 5")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The rating can't be null")
    @Min(value = 1, message = "The rating must be at least 1")
    @Max(value = 5, message = "The rating can't exceed 5")
    @Column(columnDefinition = "INT not null")
    private Integer rating;

    private String comment;

    @Column(columnDefinition = "DATETIME not null")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull(message = "The exchange can't be null")
    @ManyToOne
    @JoinColumn(name = "exchange_id", nullable = false)
    @JsonIgnore
    private Exchange exchange;

    @NotNull(message = "The reviewer account can't be null")
    @ManyToOne
    @JoinColumn(name = "reviewer_account_id", nullable = false)
    @JsonIgnore
    private Account reviewerAccount;

    @NotNull(message = "The reviewed account can't be null")
    @ManyToOne
    @JoinColumn(name = "reviewed_account_id", nullable = false)
    @JsonIgnore
    private Account reviewedAccount;
}


