package com.telerikacademy.ngpuppies.models;

import com.telerikacademy.ngpuppies.models.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private int roleId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Name")
    private RoleType name;
    
    public Role() {}
    
    public Role(RoleType name) {
        this.name = name;
    }
    
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public RoleType getName() {
        return name;
    }
    
    public void setName(RoleType name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Role{" +
            "roleId='" + roleId + '\'' +
            ", name=" + name +
            '}';
    }
}
