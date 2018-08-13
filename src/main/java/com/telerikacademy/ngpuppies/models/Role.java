package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RoleID")
    private int roleId;

    @Column(name="Name")
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
