package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class RoomDetailsScreen {
    private Scene scene;
    private MainController controller;
    private String roomTitle;
    private String roomPrice;
    private String roomImageUrl;

    public RoomDetailsScreen(MainController controller, String roomTitle, String roomPrice, String roomImageUrl) {
        this.controller = controller;
        this.roomTitle = roomTitle;
        this.roomPrice = roomPrice;
        this.roomImageUrl = roomImageUrl;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox roomDetailsContainer = new VBox();
        roomDetailsContainer.setAlignment(Pos.TOP_CENTER);
        roomDetailsContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        roomDetailsContainer.setPrefSize(900, 600); // Fixed size for the screen

        // Header background (top bar) - Increased height
        Pane headerBackground = new Pane();
        headerBackground.setPrefSize(900, 120); // Increased height of the top bar
        headerBackground.setBackground(new Background(new BackgroundFill(Color.web("#4798db"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Logo container (inside the top bar) - Adjusted for increased height
        HBox logoContainer = new HBox();
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setPrefSize(900, 120); // Same height as the header

        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/94087c9494afcb567318adb1f328134d0487e120"));
        logo.setFitWidth(100); // Smaller logo size
        logo.setPreserveRatio(true);
        logoContainer.getChildren().add(logo);

        // Back button (inside the top bar) - Created programmatically
        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.showViewAllRooms());

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

        // Main content container (left and right sides)
        HBox mainContent = new HBox(50); // Gap between left and right sides
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20)); // Padding inside the container

        // Left side: Room image, name, and price
        VBox leftSide = new VBox(10); // Gap between elements
        leftSide.setAlignment(Pos.CENTER_LEFT);

        // Room image (smaller size, same as ViewAllRooms)
        ImageView roomImage = new ImageView(new Image(roomImageUrl));
        roomImage.setFitWidth(120); // Smaller image size
        roomImage.setPreserveRatio(true);

        // Room title and price
        VBox roomTextDetails = new VBox(5); // Gap between title and price
        roomTextDetails.setAlignment(Pos.CENTER_LEFT);

        Label roomTitleLabel = new Label(roomTitle);
        roomTitleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20)); // Adjusted font size
        roomTitleLabel.setTextFill(Color.BLACK);

        Label roomPriceLabel = new Label(roomPrice);
        roomPriceLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 16)); // Adjusted font size
        roomPriceLabel.setTextFill(Color.BLACK);

        roomTextDetails.getChildren().addAll(roomTitleLabel, roomPriceLabel);

        // Add image and text details to the left side
        leftSide.getChildren().addAll(roomImage, roomTextDetails);

        // Right side: Check-in and Check-out date pickers
        VBox rightSide = new VBox(20); // Gap between date pickers
        rightSide.setAlignment(Pos.CENTER_LEFT);

        // Check-in row
        HBox checkInRow = new HBox(10); // Gap between label and date picker
        checkInRow.setAlignment(Pos.CENTER_LEFT);

        Label checkInLabel = new Label("Check-in Date:");
        checkInLabel.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        checkInLabel.setTextFill(Color.BLACK);

        DatePicker checkInDatePicker = new DatePicker();
        checkInDatePicker.setStyle("-fx-font-size: 16px;");

        checkInRow.getChildren().addAll(checkInLabel, checkInDatePicker);

        // Check-out row
        HBox checkOutRow = new HBox(10); // Gap between label and date picker
        checkOutRow.setAlignment(Pos.CENTER_LEFT);

        Label checkOutLabel = new Label("Check-out Date:");
        checkOutLabel.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        checkOutLabel.setTextFill(Color.BLACK);

        DatePicker checkOutDatePicker = new DatePicker();
        checkOutDatePicker.setStyle("-fx-font-size: 16px;");

        checkOutRow.getChildren().addAll(checkOutLabel, checkOutDatePicker);

        // Add rows to the right side
        rightSide.getChildren().addAll(checkInRow, checkOutRow);

        // Add left and right sides to the main content
        mainContent.getChildren().addAll(leftSide, rightSide);

        // Confirm button
        Button confirmButton = createActionButton("Confirm");
     // Inside the confirmButton.setOnAction method in RoomDetailsScreen.java
        confirmButton.setOnAction(e -> {
            // Get the selected dates
            LocalDate checkInDate = checkInDatePicker.getValue();
            LocalDate checkOutDate = checkOutDatePicker.getValue();

            // Validate the dates
            if (checkInDate == null || checkOutDate == null) {
                showAlert("Error", "Please select both check-in and check-out dates.");
                return;
            }

            // Other validations...

            // If all validations pass, proceed with booking
            long numDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            double pricePerNight = Double.parseDouble(roomPrice.replaceAll("[^0-9.]", ""));
            double totalPrice = pricePerNight * numDays;

            // Show a confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Booking");
            alert.setHeaderText("Are you sure you want to book this room?");
            alert.setContentText("Room: " + roomTitle + "\nCheck-in: " + checkInDate + "\nCheck-out: " + checkOutDate + "\nTotal Price: $" + totalPrice);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked "Yes", save the booking to MySQL
                boolean success = BookingManager.storeBooking(
                        controller.getLoggedInUserEmail(),
                        roomTitle,
                        checkInDate,
                        checkOutDate,
                        pricePerNight,
                        (int) numDays,
                        totalPrice
                );

                if (success) {
                    System.out.println("Booking stored successfully!");
                    // Navigate to the new confirmation screen
                    controller.showBookingConfirmation(roomTitle, roomImageUrl, checkInDate, checkOutDate, numDays, totalPrice);
                } else {
                    System.out.println("Failed to store booking.");
                }
            } else {
                // User clicked "No", do nothing and stay on the current screen
                System.out.println("Booking canceled.");
            }
        });

        // Add all components to the main container
        roomDetailsContainer.getChildren().addAll(headerPane, mainContent, confirmButton);

        // Set up the scene
        this.scene = new Scene(roomDetailsContainer, 900, 600); // Fixed size for the screen
    }

    // Helper method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Reuse the button styling logic from Dashboard
    private Button createActionButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #4899dc; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        );

        // Hover effect
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #3a87c8; " + // Darker blue on hover
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #4899dc; " + // Original color
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        ));
        return button;
    }

    public Scene getScene() {
        return scene;
    }
}