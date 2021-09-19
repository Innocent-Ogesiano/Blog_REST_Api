package com.example.week9taskinnocentogesiano.repositories;

import com.example.week9taskinnocentogesiano.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    User findByEmailOrUsername(String email, String username);
    List<User> findAllByDeleteAccount(boolean status);
}
