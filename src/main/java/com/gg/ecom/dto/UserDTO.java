package com.gg.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gg.ecom.model.Address;
import com.gg.ecom.model.PaymentAccount;
import com.gg.ecom.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long userId;
    private String username;
    private String fullname;
    private String email;
    private String userNID;
    //private String password;
    private Set<Role> roles = new HashSet<>();

    private PaymentAccount paymentAccount;
    private Address address;

    private List<String> sRoles;
    private String accessToken;

    public UserDTO() {
    }

    public UserDTO(Long userId, String username, String fullname,
                   String email, String userNID, Set<Role> roles,
                   PaymentAccount paymentAccount, Address address) {
        this.userId = userId;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.userNID = userNID;
        this.roles = roles;
        this.paymentAccount = paymentAccount;
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", userNID='" + userNID + '\'' +
                ", roles=" + roles +
                ", paymentAccount=" + paymentAccount +
                ", address=" + address +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserNID() {
        return userNID;
    }

    public void setUserNID(String userNID) {
        this.userNID = userNID;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public List<String> getsRoles() {
        return sRoles;
    }

    public void setsRoles(List<String> sRoles) {
        this.sRoles = sRoles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
