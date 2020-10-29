package com.gg.ecom.service;

import com.gg.ecom.dto.UserDTO;
import com.gg.ecom.model.ERole;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUsers(ERole role);
    UserDTO getUser(Long id);

    void removeUser(Long id);
}
