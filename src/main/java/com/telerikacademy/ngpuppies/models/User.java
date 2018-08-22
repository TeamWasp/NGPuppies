package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RoleID")
//@Table(name = "clients")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;
    
    @NotNull
    @Size(min=8, max = 15)
    @Column(name = "Username", unique = true)
    private String username;
    
    @NotNull
    @Size(min=8, max = 15)
    @Column(name = "Password")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "RoleID", insertable = false, updatable = false)
    private Role role;
    
    @NotNull
    @Size(min=10, max = 15)
    @Column(name = "EIK", unique = true)
    private String eik;
    
    public User() {
    }
    
    public User(int userId) {this.userId = userId;}
    
    public User(String username, String password, Role role, String eik) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.eik = eik;
    }
    
    public User(String username, String password, String eik) {
        this.username = username;
        this.password = password;
        this.eik = eik;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
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
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getEik() {
        return eik;
    }
    
    public void setEik(String eik) {
        this.eik = eik;
    }
}
