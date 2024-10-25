package com.bcg.bestchoisegamebackend;


import jakarta.persistence.EntityManager;
import org.postgresql.core.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class QuestionDBService {
    public static void insertUser(UUID uuid, int id1, int id2) throws SQLException {
        String sql = "INSERT INTO \"Best-choice\".questions(\"Uuid\", id1, id2)VALUES (?,?, ?)";

        Connection conn = BestChoiseGameBackendApplication.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, uuid.toString());
        pstmt.setInt(2, id1);
        pstmt.setInt(3, id2);
        pstmt.executeUpdate();

        System.out.println("Data inserted successfully.");
    }
}
