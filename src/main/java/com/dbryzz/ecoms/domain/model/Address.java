package com.dbryzz.ecoms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(max = 10)
    @Column(name = "owner_firstname")
    private String ownerFirstName;

    @NotBlank
    @Size(max = 10)
    @Column(name = "owner_lastname")
    private String ownerLastName;

    @NotBlank
    @Size(max = 10)
    @Column(name = "country")
    private String country;

    @NotBlank
    @Size(max = 10)
    @Column(name = "state")
    private String state;

    @NotBlank
    @Size(max = 10)
    @Column(name = "town")
    private String town;

    @NotBlank
    @Size(max = 15)
    @Column(name = "home_phone_number")
    private String phone;

    @JsonIgnore
    @Column(name = "users")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<User> users;



}
