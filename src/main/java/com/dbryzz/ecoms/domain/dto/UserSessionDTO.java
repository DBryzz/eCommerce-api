package com.dbryzz.ecoms.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserSessionDTO {

    private String id;
    private String fullName;
    private String username;
    private String email;
    private String NID;
    private String roles;
    private Long defaultWishListId;

}
