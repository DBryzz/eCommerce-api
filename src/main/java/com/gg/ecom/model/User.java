package com.gg.ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "user_email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id"/*, insertable = false, updatable = false*/)
    private Long userId;

    @NotBlank
    @Column(name = "username")
    @Size(max = 20)
    private String username;

    @Column(name = "fullname")
    @Size(max = 50)
    private String fullname;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(name = "user_email")
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    private PaymentAccount paymentAccount;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "addressId")
    private Address address;

    @Column(name = "user_nid")
    @Size(max = 10)
    @NotBlank
    private String userNID;

    @JsonIgnore
    @Column(name = "products")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    private List<Product> products;

    public User() {
    }

    public User(String username, String userEmail, String userNID, String password) {
        this.username = username;
        this.email = userEmail;
        this.userNID = userNID;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", paymentAccount=" + paymentAccount +
                ", address=" + address +
                ", userNID='" + userNID + '\'' +
                '}';
    }

    /*@Override
    public String toString() {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemCategory=" + itemCategory + ", itemLocation="
                + itemLocation + "]";
    }*/

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public Address getAddresses() {
        return address;
    }

    public void setAddresses(Address addresses) {
        this.address = addresses;
    }

    public String getUserNID() {
        return userNID;
    }

    public void setUserNID(String userNID) {
        this.userNID = userNID;
    }
}
