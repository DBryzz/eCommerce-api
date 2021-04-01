package com.dbryzz.ecoms.domain.controller;

import com.dbryzz.ecoms.domain.dto.*;
import com.dbryzz.ecoms.domain.repository.RoleRepository;
import com.dbryzz.ecoms.domain.repository.UserRepository;
import com.dbryzz.ecoms.domain.security.jwt.JwtUtils;
import com.dbryzz.ecoms.domain.security.service.UserDetailsImpl;
import com.dbryzz.ecoms.domain.service.CategoryService;
import com.dbryzz.ecoms.domain.service.ProductService;
import com.dbryzz.ecoms.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
//@Controller
@RestController
@RequestMapping("/api")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils, ProductService productService,
                          CategoryService categoryService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }



    @PostMapping("/public/register/is-gsm/{gsm}")
    public String createUser(@ModelAttribute @Valid UserPostDTO userPostDTO, @PathVariable("gsm") String isGsm, BindingResult result, RedirectAttributes redirectAttributes, Model model){

        String message = "";
        boolean gsmOrNot = false;

        if (result.hasErrors()) {
            message = "Please verify that all fields are properly filled";
            redirectAttributes.addFlashAttribute("errorMsg", message);

            return "redirect:/pages/register";
        }

        UserDTO userDTO = userService.createUser(userPostDTO);
        message = "Account created successfully";

        model.addAttribute("newUser", userDTO);
        redirectAttributes.addFlashAttribute("successMsg", message);

        if (isGsm.equals("yes")) {
            gsmOrNot = true;
        }

        model.addAttribute("newUser", new UserDTO());
        model.addAttribute("isGsm", Boolean.toString(gsmOrNot));

        return "redirect:/pages/authentication/is-gsm/" + isGsm;
    }


    @PostMapping("/public/login/is-gsm/{isGSM}")
    public String loginUser(@ModelAttribute @Valid UserLoginDTO userLoginDTO, @PathVariable("isGSM") String isGsm,
                            BindingResult result, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request, HttpServletResponse response) {

        String message = "";
        boolean hasGSM = false;


        if (result.hasErrors()) {
            message = "Please verify that all fields are properly filled";
            redirectAttributes.addFlashAttribute("errorMsg", message);

            return "redirect:/pages/authentication/is-gsm/"+isGsm;
        }




        UserSessionDTO sessionDTO = (UserSessionDTO) userService.loginUser(userLoginDTO, response)[0];
        TokenDTO userToken = (TokenDTO) userService.loginUser(userLoginDTO, response)[1];
        HttpSession session = request.getSession(true);


        System.out.print("User exist\n");

//        New user Cart instance for shopping
        CartDTO userCart = new CartDTO();
        userCart.setItems(new ArrayList<>());
        userCart.setTotalCartPrice(0);
        userCart.setCartSize(10);

//        Setting up user Session Attributes;
        session.setAttribute("ID_SESSION_SHOPPING_CART", userCart);
        session.setAttribute("userSessionObj", sessionDTO);
        session.setAttribute("userToken", userToken);

        message = "Welcome "+ sessionDTO.getUsername();

        redirectAttributes.addFlashAttribute("successMsg", message);


        UserSessionDTO userDTO1 = (UserSessionDTO) session.getAttribute("userSessionObj");

        TokenDTO tokenDTO1 = (TokenDTO) session.getAttribute("userToken");

        System.out.print("userId = " + userDTO1.getId() + " userNID = " + userDTO1.getNID() + " and useremail = " + userDTO1.getEmail() + "\n");
        logger.info("{}", userToken);
        logger.info(userToken.getAccessToken());
        logger.info("{}", sessionDTO);


        if (isGsm.equals("yes")) {
            return "redirect:/gsm/redirect/"+sessionDTO.getId()+"/to/gift-store/priceAsc";
        }


        return "redirect:/pages/home";
    }

    @PostMapping("/public/login1")
    public String loginUser1(@ModelAttribute @Valid UserLoginDTO userLoginDTO,
                             BindingResult result, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request, HttpServletResponse response) {


        String message = "";
        String referer = request.getHeader("Referer");



        if (result.hasErrors()) {
            message = "Please verify that all fields are properly filled";
            redirectAttributes.addFlashAttribute("errorMsg", message);

            return "redirect:/pages/authentication";
        }



        String email = userLoginDTO.getEmail();
        String passwd = userLoginDTO.getPassword();

        if (!(email.equals("domoubrice@gmail.com") || email.equals("domou.namou@go-groups.net"))) {
            System.out.print("User does not exist");
            message = "Username do not exist. \nPlease Create an Account";
            redirectAttributes.addFlashAttribute("errorMsg", message);

            return "redirect:/pages/register";
        }


        UserSessionDTO sessionDTO = new UserSessionDTO();


        if (email.equalsIgnoreCase("domoubrice@gmail.com") && passwd.equals("bryzz123")) {
            sessionDTO.setUsername("bryzz");
            sessionDTO.setEmail("domoubrice@gmail.com");
            sessionDTO.setFullName("Namou Armel");
            sessionDTO.setRoles("ROLE_USERS ROLE_BUYER ROLE_SELLER");
            sessionDTO.setId("228");
            sessionDTO.setNID("112276208");
            sessionDTO.setDefaultWishListId(Long.valueOf(1));

            System.out.println(sessionDTO.getFullName());

        }
        if (email.equalsIgnoreCase("dbryzz") && passwd.equals("bryzz123")) {
            sessionDTO.setUsername("dbryzz");
            sessionDTO.setEmail("domou.namou@go-groups.net");
            sessionDTO.setFullName("Domou Brice");
            sessionDTO.setRoles("ROLE_USERS ROLE_BUYER");
            sessionDTO.setId("102");
            sessionDTO.setNID("1122762080");
            sessionDTO.setDefaultWishListId(Long.valueOf(2));


            System.out.println(sessionDTO.getFullName());

        }

        HttpSession session = request.getSession(true);

        //        New user Cart instance for shopping
        CartDTO userCart = new CartDTO();
        userCart.setItems(new ArrayList<>());
        userCart.setTotalCartPrice(0);
        userCart.setCartSize(10);

        // Setting up user Session Attribute
        session.setAttribute("ID_SESSION_SHOPPING_CART", null);
        session.setAttribute("userSessionObj", sessionDTO);

        message = "Welcome "+ sessionDTO.getUsername();
        redirectAttributes.addFlashAttribute("successMsg", message);

        UserSessionDTO userDTO1 = (UserSessionDTO) session.getAttribute("userSessionObj");
        System.out.println(userDTO1.getFullName());
        logger.info("{}", sessionDTO);

        return "redirect:/pages/home";
    }

    @GetMapping("/public/users/logout/{userId}")
    String logoutUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String currentAuthUserId){

        userService.logoutUser(request, response);

//        HttpSession session = request.getSession(false);
//        session.invalidate();


        return "redirect:/pages/home";
    }

    /*
    @PostMapping("/public/users/become-a-seller/{username}")
    String addBuyer(@PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        UserSessionDTO sessionDTO = userService.addRole(username, UserRoles.SELLER);
        session.removeAttribute("userSessionObj");

        session.setAttribute("userSessionObj", sessionDTO);



        UserSessionDTO userDTO1 = (UserSessionDTO) session.getAttribute("userSessionObj");

        System.out.println("----------->" + userDTO1.getRoles() + "<-------------");

        return "redirect:/pages/user-profile/"+sessionDTO.getUsername();
    }



    @PostMapping("/public/get_token")
    ResponseEntity<TokenDTO> getNewToken(@RequestBody RefreshTokenDTO refreshTokenDTO){
        return ResponseEntity.ok(userService.getNewToken(refreshTokenDTO));
    }

    @PostMapping("/public/change_password")
    String changeUserPassword(@ModelAttribute @Valid ChangePasswordDTO changePasswordDTO, HttpSession session){
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("userSessionObj");
        changePasswordDTO.setEmail(userSessionDTO.getEmail());
        userService.changeUserPassword(changePasswordDTO);
        return "redirect:/pages/user-profile/"+ userSessionDTO.getUsername()+ "/getDefault";
    }

    @PostMapping("/public/update_user_details")
    String updateUserDetails(@ModelAttribute UpdateProfileDTO updateProfileDTO, HttpSession session){
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("userSessionObj");
        userService.updateProfile(updateProfileDTO, session);
        return "redirect:/pages/user-profile/"+ userSessionDTO.getUsername()+ "/getDefault";
    }

    @PostMapping("/public/forgot_password")
    ResponseEntity<?> forgotUserPassword(@RequestBody EmailDTO emailDTO){
        userService.forgotUserPassword(emailDTO);
        return ResponseEntity.noContent().build();
    }
    */


}
