package com.dbryzz.ecoms.domain.service;

import com.dbryzz.ecoms.domain.dto.UserDTO;
import com.dbryzz.ecoms.domain.dto.UserLoginDTO;
import com.dbryzz.ecoms.domain.dto.UserPostDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserDTO createUser(UserPostDTO UserPostDTO);

    UserDTO loginUser(UserLoginDTO userLoginDTO, HttpServletResponse response);

    void logoutUser(HttpServletRequest request, HttpServletResponse response);

    /*
    void changeUserPassword(ChangePasswordDTO changePasswordDTO);

    void forgotUserPassword(EmailDTO emailDTO);

    UserDTO getCurrentAuthUser();

    UserDTO getUserById(String userId);


    void updateProfile(UserUpdateDTO userUpdateDTO, HttpSession session);

    UserSessionDTO addRole(String username, UserRole role);

    TokenDTO getNewToken(RefreshTokenDTO refreshTokenDTO);

    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUsers(ERole role);
    UserDTO getUser(Long id);

    void removeUser(Long id);
    */
}
