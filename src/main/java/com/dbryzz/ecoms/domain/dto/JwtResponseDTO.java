package com.dbryzz.ecoms.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDTO {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private String NID;
    private List<String> roles;


}
