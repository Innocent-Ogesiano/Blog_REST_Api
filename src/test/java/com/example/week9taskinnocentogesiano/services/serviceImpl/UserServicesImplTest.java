package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.dto.LoginDto;
import com.example.week9taskinnocentogesiano.dto.RegistrationDto;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServicesImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServicesImpl userServices;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(2L,"Og","gee@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(3L,"Og","oge@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));

        lenient().when(userRepository.findAll()).thenReturn(userList);
        List<User> expected = userServices.getAllUsers();
        Assertions.assertEquals(expected, userList);
    }

    @Test
    void registerNewUser() {
        RegistrationDto registrationDto = new RegistrationDto("Og","og@mail.com","User","12345");
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setRole(registrationDto.getRole());
        user.setPassword(registrationDto.getPassword());
        user.setDateRegistered(Date.valueOf(LocalDate.now()));

//        given(userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername())).willReturn(null);
        lenient().when(userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername())).thenReturn(null);
//        given(userRepository.save(user)).willReturn(user);
        lenient().when(userRepository.save(user)).then(invocation -> invocation.getArgument(0));
        User savedUser = userServices.registerNewUser(registrationDto);
//        System.out.println("New user "+savedUser.getEmail());
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void loginUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(2L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(3L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("og@mail.com");
        loginDto.setPassword("12345");
        User user1 = new User();
        for (User user : userList) {
            if (user.getEmail().equals(loginDto.getEmail()) && user.getPassword().equals(loginDto.getPassword())) {
                user1 = user;
            }
        }
        lenient().when(userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())).thenReturn(user1);

        User user = userServices.loginUser(loginDto.getEmail(), loginDto.getPassword());
        assertThat(user).isNotNull();
        assertThat(user).isEqualTo(user1);
        assertThat(user.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    void editUserProfile() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(2L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
        userList.add(new User(3L,"Og","og@mail.com","Admin","12345",Date.valueOf(LocalDate.now()),null,null,false));
    }
}