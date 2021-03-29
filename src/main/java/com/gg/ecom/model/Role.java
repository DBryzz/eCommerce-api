package com.gg.ecom.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private ERole roleName;

    public Role() {

    }

    public Role(ERole roleName) {
        this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }
}
