package com.example.week9taskinnocentogesiano.repositories;

import com.example.week9taskinnocentogesiano.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
