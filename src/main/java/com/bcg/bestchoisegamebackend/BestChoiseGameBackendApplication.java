package com.bcg.bestchoisegamebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class BestChoiseGameBackendApplication {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Reactapps", "postgres", "postgres");
    }

    public static void main(String[] args) {
        SpringApplication.run(BestChoiseGameBackendApplication.class, args);
    }

}
