package com.gg.ecom.service.impl;

import com.gg.ecom.dto.UserDTO;
import com.gg.ecom.model.ERole;
import com.gg.ecom.model.Role;
import com.gg.ecom.model.User;
import com.gg.ecom.repository.RoleRepository;
import com.gg.ecom.repository.UserRepository;
import com.gg.ecom.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return loadUserDTOS(users);
    }

    @Override
    public List<UserDTO> getAllUsers(ERole role) {
        Role rol = roleRepository.findByRoleName(role).get();
        Iterable<User> users = userRepository.findByRoles(rol);

        return loadUserDTOS(users);
    }

    @Override
    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return copyUsertoUserDTO(user.get());
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
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
        userDTO.setPaymentAccount(user.getPaymentAccount());
        userDTO.setAddress(user.getAddresses());

        return userDTO;
    }
}
