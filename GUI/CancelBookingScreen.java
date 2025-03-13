package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class CancelBookingScreen {
    private Scene scene;
    private MainController controller;
    private VBox mainContent; // Main content container to hold booking items

    public CancelBookingScreen(MainController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox cancelBookingContainer = new VBox();
        cancelBookingContainer.setAlignment(Pos.TOP_CENTER);
        cancelBookingContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        cancelBookingContainer.setPrefSize(900, 600); // Fixed size for the screen

        // Header background (top bar) - Increased height
        Pane headerBackground = new Pane();
        headerBackground.setPrefSize(900, 120); // Increased height of the top bar
        headerBackground.setBackground(new Background(new BackgroundFill(Color.web("#4798db"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Logo container (inside the top bar) - Adjusted for increased height
        HBox logoContainer = new HBox();
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setPrefSize(900, 120); // Same height as the header

        // Load the logo image
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/94b87c9494afcb567318adb1f328134d0487e120"));
        logo.setFitWidth(100); // Smaller logo size
        logo.setPreserveRatio(true);
        logoContainer.getChildren().add(logo);

        // Back button (inside the top bar) - Created programmatically
        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.showDashboard());

        // Create a back arrow shape using Path
        Path backArrow = new Path();
        backArrow.setStroke(Color.WHITE); // Arrow color
        backArrow.setStrokeWidth(2); // Arrow thickness

        // Define the arrow shape
        backArrow.getElements().addAll(
                new MoveTo(15, 10), // Start at the tip of the arrow
                new LineTo(5, 15), // Draw to the left
                new LineTo(15, 20) // Draw back to the bottom
        );

        // Set the arrow as the graphic for the back button
        backButton.setGraphic(backArrow);

        // Position the back button at the top-left corner of the top bar
        StackPane headerPane = new StackPane();
        headerPane.setPrefSize(900, 120); // Same height as the header
        headerPane.getChildren().addAll(headerBackground, logoContainer, backButton);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT); // Align back button to the left
        StackPane.setMargin(backButton, new Insets(-10, 0, 0, 20)); // Move up by 10px

        // Main content container
        mainContent = new VBox(20); // Gap between elements
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20)); // Padding inside the container

        // Fetch bookings for the logged-in user
        fetchBookings();

        // Add all components to the main container
        cancelBookingContainer.getChildren().addAll(headerPane, mainContent);

        // Set up the scene
        this.scene = new Scene(cancelBookingContainer, 900, 600); // Fixed size for the screen
    }

    // Method to fetch bookings from the database
    private void fetchBookings() {
        String userEmail = controller.getLoggedInUserEmail();
        String sql = "SELECT room_type, check_in_date, check_out_date, price_per_night, num_days, total_price, room_image_url " +
                     "FROM bookings WHERE user_id = (SELECT id FROM users WHERE email = ?)";

        try (Connection conn = Database.dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String roomType = rs.getString("room_type");
                LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
                LocalDate checkOutDate = rs.getDate("check_out_date").toLocalDate();
                double pricePerNight = rs.getDouble("price_per_night");
                int numDays = rs.getInt("num_days");
                double totalPrice = rs.getDouble("total_price");
                String roomImageUrl = rs.getString("room_image_url");

                // Create a booking item
                HBox bookingItem = createBookingItem(roomType, checkInDate, checkOutDate, pricePerNight, numDays, totalPrice, roomImageUrl);
                mainContent.getChildren().add(bookingItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create a booking item
    private HBox createBookingItem(String roomType, LocalDate checkInDate, LocalDate checkOutDate, double pricePerNight, int numDays, double totalPrice, String roomImageUrl) {
        HBox bookingItem = new HBox(20); // Gap between image and details
        bookingItem.setAlignment(Pos.CENTER_LEFT);

        // Room image
        ImageView roomImage = new ImageView(new Image(roomImageUrl));
        roomImage.setFitWidth(120); // Smaller image size
        roomImage.setPreserveRatio(true);

        // Room details (aligned to the right of the image)
        VBox roomDetails = new VBox(10); // Gap between title, price, and button
        roomDetails.setAlignment(Pos.CENTER_LEFT);

        // Room title
        Label roomTitle = new Label(roomType);
        roomTitle.setFont(Font.font("Inter", FontWeight.BOLD, 20)); // Adjusted font size
        roomTitle.setTextFill(Color.BLACK);

        // Check-in and check-out dates with number of days
        Label datesLabel = new Label("Check-in: " + checkInDate + " | Check-out: " + checkOutDate + " (" + numDays + " nights)");
        datesLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 16));
        datesLabel.setTextFill(Color.BLACK);

        // Total price
        Label totalPriceLabel = new Label("Total Price: $" + totalPrice);
        totalPriceLabel.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        totalPriceLabel.setTextFill(Color.BLACK);

        // Minus button (to cancel booking)
        Button minusButton = new Button("-");
        minusButton.setStyle(
            "-fx-background-color: #ec5656; " + // Red color for cancel button
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px; " +
            "-fx-background-radius: 50%; " + // Circular button
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        );

        // Hover effect for the minus button
        minusButton.setOnMouseEntered(e -> minusButton.setStyle(
            "-fx-background-color: #d94444; " + // Darker red on hover
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px;" +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        ));

        minusButton.setOnMouseExited(e -> minusButton.setStyle(
            "-fx-background-color: #ec5656; " + // Original color
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px;" +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        ));

        // Action for the minus button (cancel booking)
        minusButton.setOnAction(e -> {
            // Show confirmation alert
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Cancellation");
            confirmAlert.setHeaderText("Are you sure you want to cancel this booking?");
            confirmAlert.setContentText("Room: " + roomType + "\nCheck-in: " + checkInDate + "\nCheck-out: " + checkOutDate);

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked "Yes", proceed with cancellation
                boolean success = cancelBooking(roomType, checkInDate, checkOutDate);

                if (success) {
                    // Show success alert
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Cancellation Successful");
                    successAlert.setHeaderText("Your booking has been canceled successfully.");
                    successAlert.showAndWait();

                    // Remove the booking item from the UI
                    mainContent.getChildren().remove(bookingItem);

                    // Optionally, refresh the ViewBookingScreen table if it's open
                    controller.refreshViewBookingScreen();
                } else {
                    // Show error alert if cancellation failed
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Cancellation Failed");
                    errorAlert.setHeaderText("An error occurred while canceling the booking.");
                    errorAlert.showAndWait();
                }
            }
            // If "No" is clicked, do nothing
        });

        // Add components to the room details
        roomDetails.getChildren().addAll(roomTitle, datesLabel, totalPriceLabel);

        // Add image and details to the booking item
        bookingItem.getChildren().addAll(roomImage, roomDetails, minusButton);

        return bookingItem;
    }

    // Method to cancel the booking in the database
    private boolean cancelBooking(String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        String sql = "DELETE FROM bookings WHERE room_type = ? AND check_in_date = ? AND check_out_date = ?";

        try (Connection conn = Database.dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomType);
            pstmt.setDate(2, java.sql.Date.valueOf(checkInDate));
            pstmt.setDate(3, java.sql.Date.valueOf(checkOutDate));

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0; // Return true if the booking was deleted successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Scene getScene() {
        return scene;
    }
}