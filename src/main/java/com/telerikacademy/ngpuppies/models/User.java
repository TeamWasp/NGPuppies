package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "RoleID")
    private Role role;

    @Column(name = "EIK")
    private String EIK;

    public User(String username, String password, Role role, String EIK) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.EIK = EIK;
    }


    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEIK() {
        return EIK;
    }

    public void setEIK(String EIK) {
        this.EIK = EIK;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
