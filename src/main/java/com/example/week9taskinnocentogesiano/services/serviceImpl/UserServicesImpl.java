package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.dto.RegistrationDto;
import com.example.week9taskinnocentogesiano.exceptions.InvalidRequestException;
import com.example.week9taskinnocentogesiano.exceptions.UserRegistrationException;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import com.example.week9taskinnocentogesiano.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private long userId;

    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewUser(RegistrationDto registrationDto) {
        User user = userRepository.findByEmailOrUsername(registrationDto.getEmail(), registrationDto.getUsername());
        if (user == null) {
            user = new User();
            Date date = Date.valueOf(LocalDate.now());
            user.setEmail(registrationDto.getEmail());
            user.setUsername(registrationDto.getUsername());
            user.setPassword(registrationDto.getPassword());
            user.setDateRegistered(date);
            user.setRole(registrationDto.getRole());
            userRepository.save(user);
            log.info("Registration Successful, Welcome!");
        } else {
            log.info("Email/Username already exist in the database");
            throw new UserRegistrationException("User with Email "+user.getEmail()+" or User with Username "
            +user.getUsername()+" already exist");
        }
        return user;
    }

    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new InvalidRequestException("Invalid Email/password");
        }
        return user;
    }

    @Override
    public User editUserProfile(long id, RegistrationDto registrationDto) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new InvalidRequestException("User not found");
        }
        user.setRole(registrationDto.getRole());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserAccount(User user) {
        if (user == null) {
            throw new InvalidRequestException("User not found");
        }
        userRepository.delete(user);
    }

    @Override
    public boolean delayDeletion(long uid) throws InterruptedException {
//        boolean status;
        User user = userRepository.getById(uid);
        if (user == null) {
            throw new InvalidRequestException("User not found");
        }
        if (!user.isDeleteAccount()) {
            user.setDeleteAccount(!user.isDeleteAccount());
            System.out.println("delete me! " + user.isDeleteAccount());
            userRepository.save(user);
        }
//        setUserId(uid);
//        Thread.sleep(5000);
//        deleteDelay();
//        status = checkDeleteStatus(user1.getUserId());
        return user.isDeleteAccount();
    }

    @Override
    public boolean cancelAccountDeletion(long uid) {
        User user = userRepository.getById(uid);
        if (user == null) {
            throw new InvalidRequestException("User not found");
        }
        if (user.isDeleteAccount()) {
            user.setDeleteAccount(!user.isDeleteAccount());
            System.out.println("cancel me! " + user.isDeleteAccount());
            userRepository.save(user);
        }
//        return checkDeleteStatus(user.getUserId());
        return user.isDeleteAccount();
    }

//    public void checkDeleteStatus(long uid) {
////        boolean status;
//        User user = userRepository.getById(uid);
//        if (user == null) {
//            throw new InvalidRequestException("User not found");
//        }
//        System.out.println("current status " + user.isDeleteAccount());
//        if (user.isDeleteAccount()) {
////                log.info("User deleted");
////            deleteUserAccount(user);
////            status = true;
//            System.out.println("User deleted");
//        } else if (!user.isDeleteAccount()) {
////                log.info("Deletion cancelled");
////            status = false;
//            System.out.println("Deletion cancelled");
//        }
//    }
}
