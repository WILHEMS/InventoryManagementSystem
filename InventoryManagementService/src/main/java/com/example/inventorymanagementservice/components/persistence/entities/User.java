package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * a user entity.
 * @author kamar baraka.*/

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String contact;

    private String role;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Address address;

    private String password;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] profilePicture;

    private int totalPoints;

    private final LocalDate registrationDate = LocalDate.now();
}
