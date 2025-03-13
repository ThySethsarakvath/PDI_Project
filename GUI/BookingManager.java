package Controller;

import Database.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingManager {

    // Method to check if the room is already booked for the selected dates
    public static boolean isRoomBooked(String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        String sql = "SELECT * FROM bookings WHERE room_type = ? AND " +
                     "((check_in_date <= ? AND check_out_date >= ?) OR " + // Overlapping dates
                     "(check_in_date <= ? AND check_out_date >= ?))";

        try (Connection conn = dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomType);
            pstmt.setDate(2, java.sql.Date.valueOf(checkOutDate)); // Check if existing booking overlaps with new check-in
            pstmt.setDate(3, java.sql.Date.valueOf(checkInDate));  // Check if existing booking overlaps with new check-out
            pstmt.setDate(4, java.sql.Date.valueOf(checkInDate)); // Check if existing booking overlaps with new check-in
            pstmt.setDate(5, java.sql.Date.valueOf(checkOutDate)); // Check if existing booking overlaps with new check-out

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // If a record is found, the room is already booked

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean storeBooking(String userEmail, String roomType, LocalDate checkInDate, LocalDate checkOutDate, double pricePerNight, int numDays, double totalPrice) {
        // Fetch room image URL from the rooms table
        String roomImageUrl = getRoomImageUrl(roomType);

        String sql = "INSERT INTO bookings (user_id, room_type, check_in_date, check_out_date, price_per_night, num_days, total_price, room_image_url) " +
                     "VALUES ((SELECT id FROM users WHERE email = ?), ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userEmail);
            pstmt.setString(2, roomType);
            pstmt.setDate(3, java.sql.Date.valueOf(checkInDate));
            pstmt.setDate(4, java.sql.Date.valueOf(checkOutDate));
            pstmt.setDouble(5, pricePerNight);
            pstmt.setInt(6, numDays);
            pstmt.setDouble(7, totalPrice);
            pstmt.setString(8, roomImageUrl); // Add room image URL

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to fetch room image URL from the rooms table
 // Helper method to fetch room image URL from the rooms table
    private static String getRoomImageUrl(String roomType) {
        String sql = "SELECT room_image_url FROM rooms WHERE room_type = ?";
        try (Connection conn = dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomType);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("room_image_url");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "https://cdn.builder.io/api/v1/image/assets/TEMP/a1e8a0e8a8668be3257bc314c7850b2c87b1bb18"; // Default image URL
    }
}