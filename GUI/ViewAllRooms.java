package Controller;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewAllRooms {
    private Scene scene;
    private MainController controller;

    public ViewAllRooms(MainController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox roomSelection = new VBox();
        roomSelection.setAlignment(Pos.TOP_CENTER);
        roomSelection.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        roomSelection.setPrefSize(900, 600); // Fixed size for the screen

        // Header background (top bar) - Increased height
        Pane headerBackground = new Pane();
        headerBackground.setPrefSize(900, 120); // Increased height of the top bar
        headerBackground.setBackground(new Background(new BackgroundFill(Color.web("#4798db"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Logo container (inside the top bar) - Adjusted for increased height
        HBox logoContainer = new HBox();
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setPrefSize(900, 120); // Same height as the header

        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/94b87c9494afcb567318adb1f328134d0487e120"));
        logo.setFitWidth(100); // Smaller logo size
        logo.setPreserveRatio(true);
        logoContainer.getChildren().add(logo);

        // Back button (inside the top bar) - Created programmatically
        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.showDashboard()); // Go back to the dashboard

        // Create a back arrow shape using Path
        Path backArrow = new Path();
        backArrow.setStroke(Color.WHITE); // Arrow color
        backArrow.setStrokeWidth(2); // Arrow thickness

        // Define the arrow shape
        backArrow.getElements().addAll(
            new MoveTo(15, 10), // Start at the tip of the arrow
            new LineTo(5, 15),  // Draw to the left
            new LineTo(15, 20)  // Draw back to the bottom
        );

        // Set the arrow as the graphic for the back button
        backButton.setGraphic(backArrow);

        // Position the back button at the top-left corner of the top bar
        StackPane headerPane = new StackPane();
        headerPane.setPrefSize(900, 120); // Same height as the header
        headerPane.getChildren().addAll(headerBackground, logoContainer, backButton);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT); // Align back button to the left
        StackPane.setMargin(backButton, new Insets(-30, 0, 0, 20)); // Add margin to the back button

        // Room grid
        GridPane roomGrid = new GridPane();
        roomGrid.setAlignment(Pos.CENTER);
        roomGrid.setHgap(50); // Horizontal gap between room items
        roomGrid.setVgap(30); // Vertical gap between room items
        roomGrid.setPadding(new Insets(20)); // Padding inside the grid

        // Add room items to the grid
        addRoomItem(roomGrid, 0, 0, "Single Rooms", "$30 night", "https://cdn.builder.io/api/v1/image/assets/TEMP/a1e8a0e8a8668be3257bc314c7850b2c87b1bb18");
        addRoomItem(roomGrid, 1, 0, "Suite Rooms", "$120 night", "https://cdn.builder.io/api/v1/image/assets/TEMP/b79779ec18acaf08233d21bcc6d48670855de0d9");
        addRoomItem(roomGrid, 0, 1, "Double Rooms", "$80 night", "https://cdn.builder.io/api/v1/image/assets/TEMP/4578ee1ecbc0efaf5047101b52246e6ceec99a7d");
        addRoomItem(roomGrid, 1, 1, "Deluxe Rooms", "$150 night", "https://cdn.builder.io/api/v1/image/assets/TEMP/b1d9266a98ffcce86a27e3e89020d142494307c9");

        // Next button container
        HBox nextButtonContainer = new HBox();
        nextButtonContainer.setAlignment(Pos.CENTER);
        nextButtonContainer.setPadding(new Insets(20)); // Padding around the button

        Button nextButton = new Button("Next");
        nextButton.setPrefSize(180, 40);
        nextButton.setStyle(
                "-fx-background-color: #4899dc; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            );
        nextButton.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));

            // Hover Effect: Darken the button color slightly
        nextButton.setOnMouseEntered(e -> nextButton.setStyle(
                "-fx-background-color: #3a87c8; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            ));
        nextButton.setOnMouseExited(e -> nextButton.setStyle(
                "-fx-background-color: #4899dc; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            ));
        nextButton.setOnAction(e -> controller.showViewAllRoomsSix());

        nextButtonContainer.getChildren().add(nextButton);

        // Add all components to the main container
        roomSelection.getChildren().addAll(headerPane, roomGrid, nextButtonContainer);

        // Set up the scene
        this.scene = new Scene(roomSelection, 900, 600); // Fixed size for the screen
    }

    private void addRoomItem(GridPane roomGrid, int col, int row, String title, String price, String imageUrl) {
        HBox roomItem = new HBox(20); // Gap between image and details
        roomItem.setAlignment(Pos.CENTER_LEFT);

        // Room image (smaller size)
        ImageView roomImage = new ImageView(new Image(imageUrl));
        roomImage.setFitWidth(120); // Smaller image size
        roomImage.setPreserveRatio(true);

        // Room details (aligned to the right of the image)
        VBox roomDetails = new VBox(10); // Gap between title, price, and button
        roomDetails.setAlignment(Pos.CENTER_LEFT);

        // Room title
        Label roomTitle = new Label(title);
        roomTitle.setFont(Font.font("Inter", FontWeight.BOLD, 20)); // Adjusted font size
        roomTitle.setTextFill(Color.BLACK);

        // Room price
        Label roomPrice = new Label(price);
        roomPrice.setFont(Font.font("Inter", FontWeight.NORMAL, 16)); // Adjusted font size
        roomPrice.setTextFill(Color.BLACK);

        // Plus button
        Button plusButton = new Button("+");
        plusButton.setStyle(
            "-fx-background-color: #4798db; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px; " +
            "-fx-background-radius: 50%; " + // Circular button
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        );

        // Hover effect for the plus button
        plusButton.setOnMouseEntered(e -> plusButton.setStyle(
            "-fx-background-color: #3a87c8; " +
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px;" +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        ));

        plusButton.setOnMouseExited(e -> plusButton.setStyle(
            "-fx-background-color: #4798db; " +
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 16px;" +
            "-fx-min-width: 30px; " +
            "-fx-min-height: 30px;"
        ));

        // Action for the plus button
        plusButton.setOnAction(e -> {
            // Check if the room is already booked
            if (BookingManager.isRoomBooked(title, LocalDate.now(), LocalDate.now().plusDays(1))) {
                showAlert("Room Booked", "This room is already booked. Please choose another room.");
            } else {
                // Navigate to the RoomDetailsScreen
                controller.showRoomDetails(title, price, imageUrl);
            }
        });

        // Add components to the room details
        roomDetails.getChildren().addAll(roomTitle, roomPrice, plusButton);

        // Add image and details to the room item
        roomItem.getChildren().addAll(roomImage, roomDetails);

        // Add room item to the grid
        roomGrid.add(roomItem, col, row); // Add to specific column and row
    }

    // Helper method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}