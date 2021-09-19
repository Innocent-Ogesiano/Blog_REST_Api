package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.model.Connection;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.ConnectionRepository;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import com.example.week9taskinnocentogesiano.services.ConnectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ConnectionServiceImpl implements ConnectionServices {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Connection addNewConnection(long uid, long uid2) {
        User user = userRepository.getById(uid);
        User user1 = userRepository.getById(uid2);
        Connection connection;
//        boolean status = false;
        if (user.getConnections() == null) {
            connection = new Connection();
            connection.getListOfConnections().add(user1);
//            Set<User> connectionList = new HashSet<>();
//            connectionList.add(user1);
//            connection.setListOfConnections(connectionList);
            user.setConnections(connection);
            connectionRepository.save(connection);
        } else {
            connection = user.getConnections();
            connection.getListOfConnections().add(user1);
            user.setConnections(connection);
        }
        if (user1.getConnections() == null) {
            connection = new Connection();
            Set<User> connectionList = new HashSet<>();
            connectionList.add(user);
            connection.setListOfConnections(connectionList);
            user1.setConnections(connection);
            connectionRepository.save(connection);
        } else {
            connection = user1.getConnections();
            connection.getListOfConnections().add(user);
            user1.setConnections(connection);
        }
        userRepository.save(user);
        userRepository.save(user1);
//        status = true;
        return connection;
    }

    @Override
    public Set<User> getUserConnections(long uid) {
        return userRepository.getById(uid).getConnections().getListOfConnections();
    }
}
