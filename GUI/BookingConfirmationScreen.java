package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class BookingConfirmationScreen {
    private Scene scene;
    private MainController controller;
    private String roomTitle;
    private String roomImageUrl;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long numDays;
    private double totalPrice;

    public BookingConfirmationScreen(MainController controller, String roomTitle, String roomImageUrl, LocalDate checkInDate, LocalDate checkOutDate, long numDays, double totalPrice) {
        this.controller = controller;
        this.roomTitle = roomTitle;
        this.roomImageUrl = roomImageUrl;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numDays = numDays;
        this.totalPrice = totalPrice;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox confirmationContainer = new VBox();
        confirmationContainer.setAlignment(Pos.TOP_CENTER);
        confirmationContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        confirmationContainer.setPrefSize(900, 600); // Fixed size for the screen

        // Header background (top bar) - Increased height
        Pane headerBackground = new Pane();
        headerBackground.setPrefSize(900, 120); // Increased height of the top bar
        headerBackground.setBackground(new Background(new BackgroundFill(Color.web("#4798db"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Add "Paid" label to the top bar
        Label paidLabel = new Label("Paid");
        paidLabel.setFont(Font.font("Inter", FontWeight.BOLD, 24)); // Larger font size
        paidLabel.setTextFill(Color.WHITE); // White text color

        // Center the "Paid" label in the top bar
        StackPane headerPane = new StackPane();
        headerPane.setPrefSize(900, 120); // Same height as the header
        headerPane.getChildren().addAll(headerBackground, paidLabel);
        StackPane.setAlignment(paidLabel, Pos.CENTER); // Center the "Paid" label

        // Main content container
        VBox mainContent = new VBox(20); // Gap between elements
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20)); // Padding inside the container

        // Room image
        ImageView roomImage = new ImageView(new Image(roomImageUrl));
        roomImage.setFitWidth(200); // Larger image size
        roomImage.setPreserveRatio(true);

        // Room title
        Label roomTitleLabel = new Label(roomTitle);
        roomTitleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 24)); // Adjusted font size
        roomTitleLabel.setTextFill(Color.BLACK);

        // Booking details
        Label checkInLabel = new Label("Check-in Date: " + checkInDate);
        checkInLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 18));
        checkInLabel.setTextFill(Color.BLACK);

        Label checkOutLabel = new Label("Check-out Date: " + checkOutDate);
        checkOutLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 18));
        checkOutLabel.setTextFill(Color.BLACK);

        Label numDaysLabel = new Label("Number of Days: " + numDays);
        numDaysLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 18));
        numDaysLabel.setTextFill(Color.BLACK);

        Label totalPriceLabel = new Label("Total Price: $" + totalPrice);
        totalPriceLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        totalPriceLabel.setTextFill(Color.BLACK);

        // Okay button
        Button okayButton = new Button("Okay");
        okayButton.setStyle(
                "-fx-background-color: #4899dc; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        );

        // Hover effect for the Okay button
        okayButton.setOnMouseEntered(e -> okayButton.setStyle(
                "-fx-background-color: #3a87c8; " + // Darker blue on hover
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        ));

        okayButton.setOnMouseExited(e -> okayButton.setStyle(
                "-fx-background-color: #4899dc; " + // Original color
                "-fx-text-fill: white; " +
                "-fx-font-weight: 700; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 10px 40px;"
        ));

        // Action for the Okay button
        okayButton.setOnAction(e -> controller.showDashboard());

        // Add all components to the main content
        mainContent.getChildren().addAll(roomImage, roomTitleLabel, checkInLabel, checkOutLabel, numDaysLabel, totalPriceLabel, okayButton);

        // Add all components to the main container
        confirmationContainer.getChildren().addAll(headerPane, mainContent);

        // Set up the scene
        this.scene = new Scene(confirmationContainer, 900, 600); // Fixed size for the screen
    }

    public Scene getScene() {
        return scene;
    }
}