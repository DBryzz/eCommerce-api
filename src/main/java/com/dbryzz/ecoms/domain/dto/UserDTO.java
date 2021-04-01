package com.dbryzz.ecoms.domain.dto;

import com.dbryzz.ecoms.domain.model.Address;
import com.dbryzz.ecoms.domain.model.PaymentAccount;
import com.dbryzz.ecoms.domain.model.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private String userId;
    private String username;
    private String fullName;
    private String email;
    private String userNID;
    private List<String> roles;
    private Long defaultWishListId;

    private PaymentAccount paymentAccount;
    private Address address;

    private List<String> sRoles;
    private String accessToken;

}