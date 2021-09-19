package com.example.week9taskinnocentogesiano.controllers;

import com.example.week9taskinnocentogesiano.dto.LoginDto;
import com.example.week9taskinnocentogesiano.dto.RegistrationDto;
import com.example.week9taskinnocentogesiano.model.Connection;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.services.ConnectionServices;
import com.example.week9taskinnocentogesiano.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;
    private final ConnectionServices connectionServices;

    @Autowired
    public UserController (UserServices userServices, ConnectionServices connectionServices) {
        this.userServices = userServices;
        this.connectionServices = connectionServices;
    }

    @GetMapping("/")
    @Cacheable(value = "userDetails")
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/register")
    public User registerNewUser(@Valid @RequestBody RegistrationDto registrationDto) {
        return userServices.registerNewUser(registrationDto);
    }

    @PostMapping("/login")
    public User userLogin(@Valid @RequestBody LoginDto loginDto) {
        User user = userServices.loginUser(loginDto.getEmail(), loginDto.getPassword());
        if (user == null) {
            log.error("Invalid email/password");
        } else {
            log.info("User successfully logged in");
        }
        return user;
    }

    @PutMapping("/editProfile/{id}")
    public User editUserProfile(@Valid @PathVariable long id,@RequestBody RegistrationDto registrationDto) {
        //        ResponseEntity<User> entity =
        return userServices.editUserProfile(id, registrationDto);
    }
    
    @DeleteMapping("/deleteAccount/{id}")
    public boolean deleteUserAccount(@PathVariable long id) throws InterruptedException {
        userServices.delayDeletion(id);
        return true;
    }

    @PutMapping("/cancelDelete/{uid}")
    public boolean cancelAccountDeletion(@PathVariable long uid) {
        return userServices.cancelAccountDeletion(uid);
    }

    @PostMapping("/addConnection/{uid}")
    public ResponseEntity<String> addNewConnection (@PathVariable long uid, @RequestParam long uid2) {
        Connection connection = connectionServices.addNewConnection(uid, uid2);
        if (connection != null) {
            return new ResponseEntity<>("Connection Added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allConnections/{uid}")
    public Set<User> getAllUserConnections(@PathVariable long uid) {
        return connectionServices.getUserConnections(uid);
    }
}
