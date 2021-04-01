package com.dbryzz.ecoms.domain.service.impl;

import com.dbryzz.ecoms.domain.dto.JwtResponseDTO;
import com.dbryzz.ecoms.domain.dto.UserDTO;
import com.dbryzz.ecoms.domain.dto.UserLoginDTO;
import com.dbryzz.ecoms.domain.dto.UserPostDTO;
import com.dbryzz.ecoms.domain.model.ERole;
import com.dbryzz.ecoms.domain.model.Role;
import com.dbryzz.ecoms.domain.model.User;
import com.dbryzz.ecoms.domain.model.WishList;
import com.dbryzz.ecoms.domain.repository.RoleRepository;
import com.dbryzz.ecoms.domain.repository.UserRepository;
import com.dbryzz.ecoms.domain.repository.WishListRepository;
import com.dbryzz.ecoms.domain.security.jwt.JwtUtils;
import com.dbryzz.ecoms.domain.security.service.UserDetailsImpl;
import com.dbryzz.ecoms.domain.service.CategoryService;
import com.dbryzz.ecoms.domain.service.ProductService;
import com.dbryzz.ecoms.domain.service.UserService;
import com.dbryzz.ecoms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private ProductService productService;
    private CategoryService categoryService;
    private WishListRepository wishListRepository


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.wishListRepository = wishListRepository;
    }

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, ProductService productService, CategoryService categoryService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public UserDTO createUser(UserPostDTO UserPostDTO) {
        return null;
    }

    @Override
    public UserDTO loginUser(UserLoginDTO userLoginDTO, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        /*JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setToken(jwt);
        jwtResponseDTO.setId(userDetails.getId());
        jwtResponseDTO.setUsername(userDetails.getUsername());
        jwtResponseDTO.setNID(userDetails.getNID());
        jwtResponseDTO.setEmail(userDetails.getEmail());
        jwtResponseDTO.setRoles(roles);*/

        User user = userRepository.findById(userDetails.getId()).get();

        UserDTO userDTO = new UserDTO();
        userDTO.setAccessToken(jwt);
        userDTO.setUserId(userDetails.getId());
        userDTO.setUsername(userDetails.getUsername());
        userDTO.setEmail(userDetails.getEmail());
        userDTO.setRoles(roles);
        userDTO.setFullName(user.getFullname());

        WishList userDefaultWishlist = wishListRepository.findWishListByBuyer_UserIdAndWishListName(userDTO.getId(), userDTO.getUsername().toLowerCase() + "-wishlist").get();
        userDTO.setDefaultWishListId(userDefaultWishlist.getWishListId());

        return userDTO;
    }

    @Override
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {

    }

    /*
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
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User Not Found: UserId - " + id);
        }
        return copyUsertoUserDTO(user.get());
    }

    @Override
    public void removeUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User Not Found: UserId - " + id);
        }
        userRepository.deleteById(id);
    }
     */

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
