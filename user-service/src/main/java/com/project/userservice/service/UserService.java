package com.project.userservice.service;

import com.project.userservice.dto.*;
import com.project.userservice.exception.PasswordNotMatchException;
import com.project.userservice.exception.UserNotFoundException;
import com.project.userservice.model.User;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    @Transactional
    UserDTO create(UserDTO user) throws DuplicateKeyException, NoSuchAlgorithmException;
    User authenticate(String email,String password) throws DuplicateKeyException, NoSuchAlgorithmException;
    UserResponse findUser(String email);
    Map<String,Object> addLocation(UUID userId,double longitude,double latitude);
    Map<String,Object> findUserWithAccountType(String email, String type) throws UserNotFoundException;
    @Transactional
    Map<String,Object> makeOrder(UUID userId,String supplierId,int quantity);
    @Transactional
    boolean changePassword(AuthRequestPasswordChange requestPasswordChange) throws PasswordNotMatchException;
}
