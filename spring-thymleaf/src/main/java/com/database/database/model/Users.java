package com.database.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @NonNull
    @Column(name="username")
    private String username;

    @NonNull
    @Column(name="email")
    private String email;


    @NonNull
    @Column(name="number")
    private long number;
    @NonNull
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;

}
