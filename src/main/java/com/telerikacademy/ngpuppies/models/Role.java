package com.telerikacademy.ngpuppies.models;

import com.telerikacademy.ngpuppies.models.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private String roleId;
    
    @Column(name = "Name")
    private RoleType name;
    
    public Role() {}
    
    public Role(String roleId, RoleType name) {
        this.roleId = roleId;
        this.name = name;
    }
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public RoleType getName() {
        return name;
    }
    
    public void setName(RoleType name) {
        this.name = name;
    }
}
