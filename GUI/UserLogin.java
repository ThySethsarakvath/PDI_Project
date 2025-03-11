package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.dataBase;

public class UserLogin {

    public static boolean authenticateUser(String email, String password) {
        String sql = "SELECT password FROM users WHERE email = ?";  // Use 'password' instead of 'password_hash'

        try (Connection conn = dataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password"); // Get stored hashed password
                return Password.verifyPassword(password, storedHash); // Verify password
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if email not found or error occurs
    }
}
