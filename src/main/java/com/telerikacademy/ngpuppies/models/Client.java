package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "clients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RoleID")
//@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID")
    private int clientId;
    
    @NotNull
    @Size(min=8, max = 15)
    @Column(name = "Username", unique = true)
    private String username;
    
    @NotNull
    @Size(min=8, max = 15)
    @Column(name = "Password")
    private String password;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "RoleID")
    private Role role;
    
    @NotNull
    @Size(min=10, max = 15)
    @Column(name = "EIK", unique = true)
    private String eik;
    
    public Client() {
    }
    
    public Client(int userId, String username, String password, Role role, String eik) {
        clientId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.eik = eik;
    }
    
    public int getClientId() {
        return clientId;
    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
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
