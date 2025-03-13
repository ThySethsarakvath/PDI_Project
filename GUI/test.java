package Controller;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import Database.dataBase;

public class test {
    public static void main(String[] args) {
        try (Connection conn = dataBase.getConnection()) {
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

