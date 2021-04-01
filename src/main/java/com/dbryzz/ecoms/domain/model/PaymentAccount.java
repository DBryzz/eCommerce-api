package com.dbryzz.ecoms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "accounts")
public class PaymentAccount {

    @Id
    @Column(name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber;

    @Column(name = "bank")
    @NotBlank
    private String bank;

    @JsonIgnore
    @Column(name = "users")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentAccount")
    private List<User> users;

    /*@Column(name = "transactions")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
    private List<Transaction> transaction;*/





}
