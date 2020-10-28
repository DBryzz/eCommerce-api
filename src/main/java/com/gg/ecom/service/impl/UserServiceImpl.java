package com.gg.ecom.service.impl;

import com.gg.ecom.dto.UserDTO;
import com.gg.ecom.model.User;
import com.gg.ecom.repository.UserRepository;
import com.gg.ecom.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return loadUserDTOS(users);
    }

    public List<UserDTO> loadUserDTOS(Iterable<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            userDTOS.add(copyUsertoUserDTO(user));
        }
        
        return userDTOS;

    }

    private UserDTO copyUsertoUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setFullname(user.getFullname());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        userDTO.setUserNID(user.getUserNID());
        userDTO.setEmail(user.getEmail());
        /*userDTO.setPaymentAccount(user.getPaymentAccount());
        userDTO.setAddress(user.getAddresses());*/

        return userDTO;
    }
}
