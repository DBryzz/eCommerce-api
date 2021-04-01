package com.dbryzz.ecoms.domain.model;

import com.dbryzz.ecoms.domain.constant.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private UserRole roleName;


}
