package com.project.userservice.service;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserResponse;
import com.project.userservice.emitters.Emitters;
import com.project.userservice.exception.UserNotFoundException;
import com.project.userservice.model.AccountType;
import com.project.userservice.model.User;
import com.project.userservice.proxy.LocationFeign;
import com.project.userservice.proxy.WaterSupplierFeign;
import com.project.userservice.repository.UserRepository;
import org.bouncycastle.oer.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Emitters emitter;
    private final LocationFeign locationFeign;
    private final WaterSupplierFeign supplierFeign;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,LocationFeign locationFeign, Emitters emitter,WaterSupplierFeign supplierFeign) {
        this.userRepository = userRepository;
        this.locationFeign = locationFeign;
        this.emitter = emitter;
        this.supplierFeign = supplierFeign;
    }

    @Override
    public UserDTO create(UserDTO user) throws DuplicateKeyException, NoSuchAlgorithmException {
        ModelMapper mapper = new ModelMapper();
        AccountType accountType = user.getAccountType();
        if(userRepository.existsUserByEmail(user.getEmail())){
            User dbUser = userRepository.findUserByEmail(user.getEmail());
            if(dbUser.getAccountTypes().contains(user.getAccountType())){
                throw  new DuplicateKeyException("User already exists in this type of role");
            }
            else{
                dbUser.getAccountTypes().add(user.getAccountType());
                if(accountType == AccountType.WATER_SUPPLIER){
                    String supplierId = createSupplierAccount(dbUser.getId());
                }
                userRepository.save(dbUser);
                user = mapper.map(dbUser,UserDTO.class);
            }
        } else if (user.getAccountType() ==null) {
            throw new NullPointerException("Please provide the account type");
        } else{
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(user.getPassword(),salt);
            User userEntity = mapper.map(user,User.class);
            userEntity.setAccountTypes(new ArrayList<>());
            userEntity.getAccountTypes().add(user.getAccountType());
            userEntity.setStoredSalt(salt);
            userEntity.setPassword(hashedPassword);
            userRepository.save(userEntity);
            user = mapper.map(userEntity,UserDTO.class);
            if(accountType == AccountType.WATER_SUPPLIER){
                String supplierId = createSupplierAccount(user.getId());
                System.out.println(supplierId);
            }
//            emitter.registerUser(user);
        }
        return user;
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
    public UserResponse findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        UserResponse userResponse;
        if(user == null){
            throw new RuntimeException("User not found");
        }else{
            ModelMapper mapper = new ModelMapper();
            UserResponse response = mapper.map(user,UserResponse.class);
            response.setAddress(locationFeign.getUserLocation(user.getId()));
            return response;
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

    @Override
    public Map<String,Object> addLocation(UUID userId,double longitude,double latitude){
        if(userId != null && userRepository.existsById(userId)){
            return  locationFeign.setLocation(userId,longitude,latitude);
        }else {
            throw new RuntimeException("User not found");
        }
    };

    private String createSupplierAccount(UUID userId){
        Map<String,String> response = supplierFeign.createAccount(userId);
        return response.get("id");
    }

    @Override
    public Map<String,Object> findUserWithAccountType(String email, String type) throws UserNotFoundException{
        Map<String,Object> response = new HashMap<>();
        User user = userRepository.findUserByEmail(email);
        AccountType accountType = refactorAccountTypeParam(type);
        if(user !=null && user.getAccountTypes().contains(accountType)){
            response.put("user",user);
            switch(accountType){
                case USER :
                    System.out.println("User");
                    response.put("user",findUser(email));
                    break;
                case TANKER :
                    System.out.println("Tanker");
                    break;
                case WATER_SUPPLIER:
                    System.out.println("Supplier");
                    response.put("supplier",supplierFeign.getSupplierAccount(user.getId()));
                    break;
                case TANKER_SUPPLIER :
                    System.out.println("Tanker Supplier");
                    break;
            }
            return response;
        }else{
            throw new UserNotFoundException("User not Found");
        }
    }

    private AccountType refactorAccountTypeParam(String type){
        return switch (type.toLowerCase()) {
            case "user" -> AccountType.USER;
            case "tanker" -> AccountType.TANKER;
            case "water-supplier" -> AccountType.WATER_SUPPLIER;
            case "tanker-supplier" -> AccountType.TANKER_SUPPLIER;
            default -> throw new RuntimeException("Account type not found");
        };
    }
}
