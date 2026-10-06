package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class CompanyProfile {

    @Id
    private Integer id;


   /* @NotNull(message = "The name cant be null")
    @NotEmpty(message = "The name cant be empty")
    @NotBlank(message = "The name cant be blank")
    @Size(max = 150, message = "Name must not exceed 150 characters")*/
    @Column(columnDefinition = "VARCHAR(150) not null")
    private String name;


/*
    @Size(max = 500, message = "Description must not exceed 500 characters")
*/
    @Column(columnDefinition = "VARCHAR(500)")
    private String description;


/*    @NotNull(message = "The phone cant be null")
    @NotEmpty(message = "The phone cant be empty")
    @NotBlank(message = "The phone cant be blank")
    @Pattern(
            regexp = "^05[0-9]{8}$",
            message = "Phone must be 10 digits and start with 05"
    )*/
    @Check(constraints = "phone REGEXP '^05[0-9]{8}$'")
    @Column(columnDefinition = "VARCHAR(10) not null unique")
    private String phone;


/*
    @Size(max = 100, message = "City must not exceed 100 characters")
*/
    @Column(columnDefinition = "VARCHAR(100)")
    private String city;


/*
    @Size(max = 500, message = "Logo must not exceed 500 characters")
*/
    @Column(columnDefinition = "VARCHAR(500)")
    private String logo;


/*
    @NotNull(message = "Verified cant be null")
*/
    @Column(columnDefinition = "BOOLEAN not null DEFAULT FALSE")
    private Boolean verified = false;


    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
}