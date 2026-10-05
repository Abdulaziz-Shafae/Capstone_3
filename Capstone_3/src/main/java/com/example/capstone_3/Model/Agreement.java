package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agreement {

    @Id
    private Integer id;

    @NotBlank(message = "The agreement content can't be blank")
    @Column(columnDefinition = "TEXT not null")
    private String content;

    @Column(columnDefinition = "BOOLEAN not null DEFAULT FALSE")
    private Boolean providerAccepted = false;

    @Column(columnDefinition = "BOOLEAN not null DEFAULT FALSE")
    private Boolean receiverAccepted = false;

    @NotNull(message = "The exchange can't be null")
    @OneToOne
    @MapsId
    @JoinColumn(name = "exchange_id", unique = true, nullable = false)
    @JsonIgnore
    private Exchange exchange;
}
