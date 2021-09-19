package com.example.week9taskinnocentogesiano.services;

import com.example.week9taskinnocentogesiano.model.Connection;
import com.example.week9taskinnocentogesiano.model.User;

import java.util.Set;

public interface ConnectionServices {
    Connection addNewConnection(long uid, long uid2);
    Set<User> getUserConnections(long uid);
}
