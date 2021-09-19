package com.example.week9taskinnocentogesiano.services;

import com.example.week9taskinnocentogesiano.dto.RegistrationDto;
import com.example.week9taskinnocentogesiano.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    List<User> getAllUsers();
    User registerNewUser(RegistrationDto registrationDto);
    User loginUser(String email, String password);
    User editUserProfile(long id, RegistrationDto registrationDto);
    void deleteUserAccount(User user) throws InterruptedException;
    boolean delayDeletion(long uid) throws InterruptedException;
    boolean cancelAccountDeletion(long uid);
}
