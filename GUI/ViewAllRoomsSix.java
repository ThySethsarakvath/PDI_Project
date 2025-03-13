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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewAllRoomsSix {
    private Scene scene;
    private MainController controller;

    public ViewAllRoomsSix(MainController controller) {
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
        backButton.setOnAction(e -> controller.showViewAllRooms()); // Go back to the dashboard

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
        backButton.setGraphic(backArrow); // Custom style for back button
//        backButton.setOnAction(e -> controller.showDashboard()); // Go back to the dashboard

        // Position the back button at the top-left corner of the top bar
        StackPane headerPane = new StackPane();
        headerPane.setPrefSize(900, 120); // Same height as the header
        headerPane.getChildren().addAll(headerBackground, logoContainer, backButton);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT); // Align back button to the left
        StackPane.setMargin(backButton, new Insets(-10, 0, 0, 20)); // Move up by 10px

        // Room grid
        GridPane roomGrid = new GridPane();
        roomGrid.setAlignment(Pos.CENTER);
        roomGrid.setHgap(50); // Horizontal gap between room items
        roomGrid.setVgap(30); // Vertical gap between room items
        roomGrid.setPadding(new Insets(20)); // Padding inside the grid

        // Add room items to the grid (6 rooms in 3 rows and 2 columns)
        addRoomItem(roomGrid, 0, 0, "Executive Rooms", "$200 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/ffbcbf30b4e240b93086411fd4dffef53774ca7f833e22909d3523c6764a68f0?apiKey=b13786b9631c451597743fcb810395dc&");
        addRoomItem(roomGrid, 1, 0, "Family Rooms", "$180 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/e5930136e586d1efe8aa442f8e43c3eadb0f7c6b875f6fc875cf36bba7774542?apiKey=b13786b9631c451597743fcb810395dc&");
        addRoomItem(roomGrid, 0, 1, "President Rooms", "$300 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/1b15979cfd69e421f5960dd3ae0743c8ee8cceb9cf552fb3443a885ac7434832?apiKey=b13786b9631c451597743fcb810395dc&");
        addRoomItem(roomGrid, 1, 1, "Studio Rooms", "$100 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/bd23461419be1ba1f7d2d42060e701ca5349de853e96c7a58fdb64b5e53cb7e1?apiKey=b13786b9631c451597743fcb810395dc&");
        addRoomItem(roomGrid, 0, 2, "Honeymoon Rooms", "$220 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/18df3ef8a8b4bb6c48425fd223c499e383c82a1a12f479587011125540a850e4?apiKey=b13786b9631c451597743fcb810395dc&"); // Replace with your image URL
        addRoomItem(roomGrid, 1, 2, "Economic Rooms", "$40 night", "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/cb68d65d14bb6284aa386f26ec66baa0c252bed81ce4fa68b6bbf07f0cede7ab?apiKey=b13786b9631c451597743fcb810395dc&"); // Replace with your image URL

        // Add all components to the main container
        roomSelection.getChildren().addAll(headerPane, roomGrid);

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

    // Reuse the button styling logic from Dashboard
//    private Button createActionButton(String text) {
//        Button button = new Button(text);
//        button.setStyle(
//                "-fx-background-color: #4798db; " +
//                "-fx-text-fill: white; " +
//                "-fx-font-weight: 700; " +
//                "-fx-font-size: 16px; " +
//                "-fx-background-radius: 50%; " + // Circular button
//                "-fx-min-width: 30px; " +
//                "-fx-min-height: 30px;"
//            );
//        button.setOnMouseEntered(e -> button.setStyle(
//                    "-fx-background-color: #3a87c8; " + 
//                    "-fx-background-radius: 50%; " +
//                    "-fx-text-fill: white; " + 
//                    "-fx-font-weight: 700; " +
//                    "-fx-font-size: 16px;"+
//                    "-fx-min-width: 30px; " +
//                    "-fx-min-height: 30px;"
//                ));
//        button.setOnMouseExited(e -> button.setStyle(
//                    "-fx-background-color: #4798db; " + 
//                    "-fx-background-radius: 50%; " +
//                    "-fx-text-fill: white; " + 
//                    "-fx-font-weight: 700; " +
//                    "-fx-font-size: 16px;"+
//                    "-fx-min-width: 30px; " +
//                    "-fx-min-height: 30px;"
//                ));

//        return button;
//    }

    public Scene getScene() {
        return scene;
    }
}