package com.dbryzz.ecoms.domain.controller;

import com.dbryzz.ecoms.domain.constant.UserRole;
import com.dbryzz.ecoms.domain.model.Product;
import com.dbryzz.ecoms.domain.repository.UserRepository;
import com.dbryzz.ecoms.domain.security.service.UserDetailsImpl;
import com.dbryzz.ecoms.domain.service.CategoryService;
import com.dbryzz.ecoms.domain.dto.UserDTO;
import com.dbryzz.ecoms.domain.model.Role;
import com.dbryzz.ecoms.domain.model.User;
import com.dbryzz.ecoms.domain.payload.request.LoginRequest;
import com.dbryzz.ecoms.domain.payload.request.SignupRequest;
import com.dbryzz.ecoms.domain.payload.request.UpdateSignupRequest;
import com.dbryzz.ecoms.domain.dto.JwtResponseDTO;
import com.dbryzz.ecoms.domain.dto.MessageResponseDTO;
import com.dbryzz.ecoms.domain.repository.RoleRepository;
import com.dbryzz.ecoms.domain.security.jwt.JwtUtils;
import com.dbryzz.ecoms.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/auth")
@SessionAttributes("sessionInfo")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils,
                          ProductService productService,
                          CategoryService categoryService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/login-page")
    public String showLoginPage(@ModelAttribute LoginRequest loginRequest) {
        return "cssandjs/login-page";
    }




//    @RequestMapping(method = RequestMethod.POST, path = "/signin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //@ResponseBody
    /*@PostMapping("/signin")
    public String authenticationProcess(@ModelAttribute Product product, @ModelAttribute @Valid LoginRequest loginRequest, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "cssandjs/login-page";
        }

        System.out.println(loginRequest.getUsername() + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getNID(),
                roles);

        UserDTO user = new UserDTO();
        user.setAccessToken(jwtResponseDTO.getAccessToken());
        user.setUserId(jwtResponseDTO.getId());
        user.setUsername(jwtResponseDTO.getUsername());
        user.setEmail(jwtResponseDTO.getEmail());
        user.setsRoles(jwtResponseDTO.getRoles());


        model.addAttribute("sessionInfo", user);
//        session.setAttribute("jwtResponse", user);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getCategories());


        return "cssandjs/products";
    }
*/
/*
//    @RequestMapping(method = RequestMethod.POST, path = "/signin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @SessionAttributes("")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticationProcess(LoginRequest loginRequest) {

        System.out.println(loginRequest.getUsername() + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getNID(),
                        roles));
    }
*/



    /*@SessionAttributes("")
    @PostMapping("/signin")
    public String authenticateUser(@Valid LoginRequest loginRequest, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "login-page";
        }


        ResponseEntity userJwtResponse = authenticationProcess(loginRequest);
        model.addAttribute("response", userJwtResponse);

        return "index";
    }*/


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Email is already in use!"));
        }

        String userNID = signUpRequest.getNID();

        if (userRepository.existsByUserNID(userNID)) {
            User existingUser = userRepository.findByUserNID(userNID).get();
            Set<Role> existingUserRoles = existingUser.getRoles();

            for (Role rol : existingUserRoles) {
                if (rol.getRoleName().toString().equalsIgnoreCase("SELLER")) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponseDTO("Error: NID \"" + userNID + "\" is already a SELLER!"));
                }
            }
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getNID(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(UserRole.ROLE_BUYER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(UserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "seller":
                            Role sellerRole = roleRepository.findByRoleName(UserRole.ROLE_SELLER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(sellerRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(UserRole.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }

    @PatchMapping("/user/update-login")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('BUYER')")
    public ResponseEntity<?> editLoginDetails(@Valid @RequestBody UpdateSignupRequest signUpRequest) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).get();

        if (signUpRequest.getEmail().equals("")) {
            signUpRequest.setEmail(user.getEmail());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail()) && !(signUpRequest.getEmail().equals(user.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Email is already in use!"));
        }

        // Verify that user knows previous password

        if (!isAuthenticatedNow(user.getUsername(), signUpRequest.getPreviousPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("The Previous Password did not match"));
        }




        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getNewPassword()));


        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDTO("Login Details Changed successfully!"));
    }


    @PatchMapping("/user/become-seller")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<?> becomeASeller(@Valid @RequestBody SignupRequest signUpRequest) {

        String userNID = signUpRequest.getNID();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("not found");
        }

        User user = userOptional.get();

        if (!(user.getUsername().equals(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Previous and Current Usernames must be identical"));
        }


        if (!(user.getUserNID().equals(signUpRequest.getNID()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Previous and Current NID must be identical"));
        }

        if (userRepository.existsByUserNID(userNID)) {
            User existingUser = userRepository.findByUserNID(userNID).get();
            Set<Role> existingUserRoles = existingUser.getRoles();

            for (Role rol : existingUserRoles) {
                if (rol.getRoleName().toString().equalsIgnoreCase("SELLER")) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponseDTO("Error: NID \"" + userNID + "\" is already a SELLER!"));
                }
            }
        }

        signUpRequest.getRole().forEach(role -> {
            if (!("role_" + role).toUpperCase().equals("ROLE_SELLER")) {throw new RuntimeException("Role must be SELLER");}
        });

        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        Role sellerRole = roleRepository.findByRoleName(UserRole.ROLE_SELLER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(sellerRole);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDTO(user.getUsername() + " is now a Seller!"));
    }

    public boolean isAuthenticatedNow(String userName, String oldPassword ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, oldPassword));



        if (!(authentication.getName().equals(userName))) {
            return false;
        }

        return true;
    }


}
