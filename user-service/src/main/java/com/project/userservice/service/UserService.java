package com.project.userservice.service;

import com.project.userservice.dto.AuthRequest;
import com.project.userservice.dto.AuthResponse;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.model.User;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    @Transactional
    UserDTO create(UserDTO user) throws DuplicateKeyException, NoSuchAlgorithmException;
    User authenticate(String email,String password) throws DuplicateKeyException, NoSuchAlgorithmException;
    User findUser(String email);
}
