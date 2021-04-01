package com.dbryzz.ecoms.domain.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String header;
    private String issuer;
    private String accessToken;
    private String type;

}
