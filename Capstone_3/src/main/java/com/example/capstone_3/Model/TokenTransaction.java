package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Check(constraints = "type IN ('STARTING', 'TEACHING', 'LEARNING', 'BONUS', 'REFUND')")
public class TokenTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The token amount can't be null")
    @Column(columnDefinition = "INT not null")
    private Integer amount;

    @NotNull(message = "The transaction type can't be null")
    @Pattern(regexp = "^(STARTING|TEACHING|LEARNING|BONUS|REFUND|PURCHASE|REDEMPTION)$", message = "The transaction type must be STARTING, TEACHING, LEARNING, BONUS, REFUND, PURCHASE or REDEMPTION")
    @Column(columnDefinition = "VARCHAR(20) not null")
    private String type;

    private String description;

    @Column(columnDefinition = "DATETIME not null")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull(message = "The account can't be null")
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "exchange_id")
    @JsonIgnore
    private Exchange exchange;
}


