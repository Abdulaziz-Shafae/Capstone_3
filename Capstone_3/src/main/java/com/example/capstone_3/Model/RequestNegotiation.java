package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestNegotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime proposedDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "int not null default 0")
    private Integer urgentTokens = 0;

    @Column(columnDefinition = "int not null default 0")
    private Integer weekendTokens = 0;

    @ManyToOne
    @JoinColumn(name = "learning_request_id", nullable = false)
    @JsonIgnore
    private LearningRequest learningRequest;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    @JsonIgnore
    private Account senderAccount;

    // Return identities without exposing Account passwords or recursive relationships.
    public Integer getLearningRequestId() {
        return learningRequest == null ? null : learningRequest.getId();
    }

    public Integer getSenderAccountId() {
        return senderAccount == null ? null : senderAccount.getId();
    }
}
