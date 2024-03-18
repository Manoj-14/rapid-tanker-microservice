package com.project.userservice.service;

import com.project.userservice.dto.AuthRequest;
import com.project.userservice.dto.AuthResponse;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.model.User;
import com.project.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO user) throws DuplicateKeyException, NoSuchAlgorithmException {
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new DuplicateKeyException("User Already exists");
        }
        else{
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(user.getPassword(),salt);
            ModelMapper mapper = new ModelMapper();
            User userEntity = mapper.map(user,User.class);
            userEntity.setStoredSalt(salt);
            userEntity.setPassword(hashedPassword);
            userRepository.save(userEntity);
            user = mapper.map(userEntity,UserDTO.class);
            return user;
        }
    }

    @Override
    public User authenticate(String email,String password) throws DuplicateKeyException, NoSuchAlgorithmException {
        if (email.isEmpty() || password.isEmpty()) throw new RuntimeException("Unauthorized");

        var user = userRepository.findUserByEmail(email);

        if(user == null) throw new RuntimeException("unauthorized");

        var verified = verifyPasswordHash(password,user.getPassword(), user.getStoredSalt());

        if(!verified) throw new RuntimeException("unauthorized");

        return user;
    }

    @Override
    public User findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found");
        }else{
            return user;
        }
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    private Boolean verifyPasswordHash(String password, byte[] hashedPassword, byte[] storedSalt) throws NoSuchAlgorithmException {
        if (password.isBlank() || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty or whitespace only string.");
        if (hashedPassword.length != 64)
            throw new IllegalArgumentException("Invalid length of password hash (64 bytes expected)");
        if (storedSalt.length != 128)
            throw new IllegalArgumentException("Invalid length of password salt (64 bytes expected).");

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(storedSalt);
        var computedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return MessageDigest.isEqual(computedHash, hashedPassword);
    }

}
