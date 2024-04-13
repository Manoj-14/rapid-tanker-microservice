package com.project.userservice.controller;

import com.project.userservice.dto.AuthRequest;
import com.project.userservice.dto.AuthResponse;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserResponse;
import com.project.userservice.model.AccountType;
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
import java.util.Map;
import java.util.UUID;

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
        AuthResponse response = new AuthResponse(jwtService.generateToken(user.getEmail()));
        return response;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        UserResponse response = userService.findUser(email);
        return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
    }

    @PostMapping("/location/{userId}")
    public ResponseEntity<?> addLocation(@PathVariable("userId") UUID userId, @RequestBody Map<String,String> request){
        String locationId = request.get("locationId");
        return new ResponseEntity<>(userService.addLocation(userId,locationId),HttpStatus.CREATED);
//        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> findUser(@PathVariable("email") String email, @RequestParam("type") String type){
        Map<String,Object> response = userService.findUserWithAccountType(email,type);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
