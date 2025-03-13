package Controller;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Dashboard {
    private MainController controller;
    private Scene scene;

    public Dashboard(MainController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox hotelBookingContainer = new VBox();
        hotelBookingContainer.setPrefSize(900, 600); // Resized to 900x600

        // Booking Container
        HBox bookingContainer = new HBox();
        bookingContainer.setStyle("-fx-background-color: rgba(71, 152, 219, 0.5);");
        bookingContainer.setPrefSize(900, 600);

        // Sidebar
        VBox sidebar = new VBox(25); // 15px spacing between buttons
        sidebar.setPrefWidth(252); // 28% of 900
        sidebar.setStyle("-fx-background-color: #5faae7;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(110, 20, 0, 20)); // Adjusted padding

        // Hotel Logo
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/07f3457a919135d052e8e6302d346bb9bee623021ffe4133aa11242967881bfd?apiKey=b13786b9631c451597743fcb810395dc&"));
        logo.setFitWidth(100); // Adjusted size
        logo.setFitHeight(100);
        logo.setStyle("-fx-border-radius: 15px;");

        // Sidebar Buttons
        Button viewRoomsButton = createActionButton("View Rooms");
        Button viewBookingButton = createActionButton("View Booking");
        Button cancelBookingButton = createActionButton("Cancel Booking");
        Button exitButton = createExitButton("Exit");
        
        viewRoomsButton.setOnAction(e -> controller.showViewAllRooms());
        viewBookingButton.setOnAction(e -> controller.showViewBooking());
        cancelBookingButton.setOnAction(e -> controller.showCancelBooking());


        // Add components to sidebar
        sidebar.getChildren().addAll(logo, viewRoomsButton, viewBookingButton, cancelBookingButton, exitButton);

        // Main Content
        VBox mainContent = new VBox();
        mainContent.setPrefWidth(648); // 72% of 900
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(50, 20, 0, 20)); // Adjusted padding

        // Background Image
        ImageView backgroundImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/e1f99e2972d0cd3298f539f1e40a45f16ba4095837860bbdc1b5d6a471d4db8a?apiKey=b13786b9631c451597743fcb810395dc&"));
        backgroundImage.setFitWidth(648); // Ensure it covers the main content width
        backgroundImage.setFitHeight(600); // Set height
        backgroundImage.setPreserveRatio(false); // Make sure it fills the space

        // Spacer for pushing the navigation down (30% from the top)
        Region spacer = new Region();
        spacer.setPrefHeight(80); // 30% of 600px (main content height)

        // Navigation Buttons (Now styled the same as sidebar buttons)
        HBox navigation = new HBox(20);
        navigation.setAlignment(Pos.CENTER);
        navigation.setPadding(new Insets(20, 0, 0, 0)); // Adjusted padding

        Button homeButton = createActionButton("Home");
        Button aboutUsButton = createActionButton("About us");
        Button profileButton = createActionButton("Profile");
        
        aboutUsButton.setOnAction(e -> controller.showAboutUsPage());
        profileButton.setOnAction(e-> controller.showprofile());

        navigation.getChildren().addAll(homeButton, aboutUsButton, profileButton);

        // Wrapper for positioning navigation
        VBox navigationWrapper = new VBox(spacer, navigation);
        navigationWrapper.setAlignment(Pos.TOP_CENTER);

        // StackPane to overlay navigation on background
        StackPane contentWrapper = new StackPane();
        contentWrapper.getChildren().addAll(backgroundImage, navigationWrapper);
        StackPane.setAlignment(navigationWrapper, Pos.TOP_CENTER);

        // Add sidebar and main content to booking container
        bookingContainer.getChildren().addAll(sidebar, contentWrapper);

        // Add booking container to main container
        hotelBookingContainer.getChildren().add(bookingContainer);

        // Set up the scene
        this.scene = new Scene(hotelBookingContainer, 900, 600);
    }

    // Updated Button Styling (for both Sidebar & Navigation)
    private Button createActionButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(180, 40); // Same size as sidebar buttons
        button.setStyle(
            "-fx-background-color: #4899dc; " + 
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " + 
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        );
        button.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));

        // Hover Effect: Darken the button color slightly
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: #3a87c8; " + 
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " + 
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: #4899dc; " + 
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " + 
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));

        return button;
    }

    private Button createExitButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(180, 40);
        button.setStyle(
            "-fx-background-color: #ec5656; " +
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700;"
        );
        button.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));

        // Hover Effect: Darken the button color slightly
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: #d94444; " + 
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " + 
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: #ec5656; " + 
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " + 
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));
        
        button.setOnAction(e -> showExitConfirmation());
        

        return button;
    }
    
    private void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Choose 'Yes' to exit or 'No' to stay.");

        ButtonType yesButton = new ButtonType("Yes", ButtonType.OK.getButtonData());
        ButtonType noButton = new ButtonType("No", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            // Show the Thank You screen before exiting
            ThankYouScreen thankYouScreen = new ThankYouScreen();
            Scene thankYouScene = thankYouScreen.getScene();
            controller.getPrimaryStage().setScene(thankYouScene);
            controller.getPrimaryStage().setTitle("Thank You");
            controller.getPrimaryStage().show();

            // Exit the application after a delay (optional)
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },
                5000 // 5-second delay before exiting
            );
        }
    }


    public Scene getScene() {
        return scene;
    }
}