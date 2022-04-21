package com.example.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity(name = "User")
@Table(
        name = "user",
        schema = "public"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @NotEmpty(message = "Name must not be empty")
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @NotNull(message = "Gender must not be empty")
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String gender;

    @NotEmpty(message = "Email cannot be empty")
    @Column(
            name = "email",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String email;

    @NotEmpty
    @Column(
            name = "username",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String username;

    @NotNull
    @NotEmpty
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @NotNull
    @Column(
            name = "date_created",
            nullable = false
    )
    private Timestamp dateCreated;

    @NotNull
    @Column(
            name = "last_updated",
            nullable = false
    )
    private Timestamp lastUpdated;

    @Column(
            name = "last_login"
    )
    private Timestamp lastLogin;

}


