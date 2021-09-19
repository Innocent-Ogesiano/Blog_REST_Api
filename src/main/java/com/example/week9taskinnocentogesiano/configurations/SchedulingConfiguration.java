package com.example.week9taskinnocentogesiano.configurations;

import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
@Slf4j
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class SchedulingConfiguration {
    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 20000L)
    void deleteDelay() {
        List<User> userList = userRepository.findAllByDeleteAccount(true);
        if (userList.size() == 0) {
//            throw new InvalidRequestException("No users found");
            log.info("No users found");
        } else {
            userRepository.deleteAll(userList);
            System.out.println("Success");
        }
    }

}
