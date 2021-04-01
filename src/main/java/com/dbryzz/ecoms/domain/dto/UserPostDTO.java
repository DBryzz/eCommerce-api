package com.dbryzz.ecoms.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserPostDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 10)
    @Email
    private String NID;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
