package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"session_id", "exchange_id"})
})
@Check(constraints = "status IN ('JOINED', 'ATTENDED', 'ABSENT', 'CANCELLED')")
public class SessionParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The participant status can't be blank")
    @Pattern(regexp = "^(JOINED|ATTENDED|ABSENT|CANCELLED)$", message = "The status must be JOINED, ATTENDED, ABSENT, or CANCELLED")
    @Column(columnDefinition = "VARCHAR(20) not null DEFAULT 'JOINED'")
    private String status = "JOINED";

    @NotNull(message = "The session can't be null")
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    @JsonIgnore
    private Session session;

    @NotNull(message = "The exchange can't be null")
    @ManyToOne
    @JoinColumn(name = "exchange_id", nullable = false)
    @JsonIgnore
    private Exchange exchange;
}


