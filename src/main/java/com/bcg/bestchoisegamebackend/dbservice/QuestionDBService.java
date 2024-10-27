package com.bcg.bestchoisegamebackend.dbservice;

import com.bcg.bestchoisegamebackend.config.ConfigurationBean;
import com.bcg.bestchoisegamebackend.entity.QuestionModel;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@Service
public class QuestionDBService {
    public static void insertUser(UUID uuid, int id1, int id2) throws SQLException {
        String sql = "INSERT INTO \"Best-choice\".questions(\"Uuid\", id1, id2)VALUES (?,?, ?)";

        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);

        pstmt.setString(1, uuid.toString());
        pstmt.setInt(2, id1);
        pstmt.setInt(3, id2);
        pstmt.executeUpdate();

        System.out.println("Data inserted successfully.");
    }

    public static boolean isExist(UUID uuid) throws SQLException {
        String sql = "SELECT count(*) FROM \"Best-choice\".questions qs\n" +
                "WHERE qs.\"Uuid\" = ?";


        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);

        pstmt.setString(1, uuid.toString());
        pstmt.executeQuery();

        System.out.println("Data return successfully.");

        return Objects.equals(pstmt.executeQuery().toString(), "1");
    }

    public static QuestionModel getById(UUID uuid) throws SQLException {
        String sql = "SELECT * FROM \"Best-choice\".questions qs\n" +
                "WHERE qs.\"Uuid\" = ?";

        PreparedStatement pstmt = ConfigurationBean.conn.prepareStatement(sql);

        pstmt.setString(1, uuid.toString());
        pstmt.executeQuery();

        System.out.println("Data return successfully.");
        QuestionModel questionModel = new QuestionModel();
        ResultSet rs = pstmt.executeQuery();
        questionModel.setId1(rs.getInt("id1"));
        questionModel.setId2(rs.getInt("id2"));
        questionModel.setUuid(uuid.toString());

        return questionModel;
    }

    public static void deleteQuestion(UUID uuid) {
        System.out.println("Deleting question: " + uuid);
    }
}
