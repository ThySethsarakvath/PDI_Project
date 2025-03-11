package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.dataBase;

public class UserRegistration {

    public static boolean registerUser(String username, String email, String phone, String password) {
    	String hashedPassword = Password.hashPassword(password);
    	String sql = "INSERT INTO users ( username, phone_number,email, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataBase.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


