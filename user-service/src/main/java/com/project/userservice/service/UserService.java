package com.project.userservice.service;

import com.project.userservice.dto.AuthRequest;
import com.project.userservice.dto.AuthResponse;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserResponse;
import com.project.userservice.exception.UserNotFoundException;
import com.project.userservice.model.AccountType;
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
}
