package com.project.userservice.controller;

import com.project.userservice.dto.AuthRequest;
import com.project.userservice.dto.AuthResponse;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.model.User;
import com.project.userservice.service.JWTService;
import com.project.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    @Autowired
    public UserController(UserService userService,JWTService jwtService) {
        this.userService = userService;
        this.jwtService=jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody  UserDTO userDTO){
        try {
            UserDTO user = this.userService.create(userDTO);
            return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
        }catch (DuplicateKeyException dke){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"User already exists");
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error Found");
        }
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException {
        User user = userService.authenticate(authRequest.getEmail(),authRequest.getPassword());
        ModelMapper mapper = new ModelMapper();
        AuthResponse response = mapper.map(user,AuthResponse.class);
        response.setToken(jwtService.generateToken(user.getEmail()));
        return response;
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email){
        User user = userService.findUser(email);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
