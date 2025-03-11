package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        BorderPane dashboardContainer = new BorderPane();
        dashboardContainer.setPrefSize(900, 600);

        // Background Image
        ImageView backgroundImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/44f397c62e78d878fbff270b69abc52be1b8c87e0fbaf89b845a8fecf4a16f4c"));
        backgroundImage.setFitWidth(900);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);

        // Overlay
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(71, 152, 219, 0.5);");
        overlay.setPrefSize(900, 600);

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPrefWidth(250);
        sidebar.setPrefHeight(600);
        sidebar.setStyle("-fx-background-color: #5faae7;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(30, 10, 10, 10));

        // User Avatar
        ImageView avatar = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/07f3457a919135d052e8e6302d346bb9bee623021ffe4133aa11242967881bfd"));
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);
        avatar.setStyle("-fx-border-radius: 15px;");

        // Sidebar Buttons
        Button viewRoomsButton = createSidebarButton("View Rooms");
        Button viewBookingButton = createSidebarButton("View Booking");
        Button cancelBookingButton = createSidebarButton("Cancel Booking");
        Button exitButton = createExitButton("Exit");

        sidebar.getChildren().addAll(avatar, viewRoomsButton, viewBookingButton, cancelBookingButton, exitButton);

        // Header Navigation inside content area
     // Header Navigation inside content area
        HBox navMenu = new HBox(15);
        navMenu.setAlignment(Pos.CENTER);
//        navMenu.setPadding(new Insets(10, 10, 10, 10));
        navMenu.setStyle("-fx-background-color: rgba(71, 152, 219, 0.5); -fx-border-radius: 15px;");
        navMenu.setMaxWidth(Double.MAX_VALUE);

        Button homeButton = createNavButton("Home");
        Button aboutUsButton = createNavButton("About Us");
        Button profileButton = createNavButton("Profile");

        navMenu.getChildren().addAll(homeButton, aboutUsButton, profileButton);

        // Content Pane with background image
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(backgroundImage, overlay);

        VBox contentLayout = new VBox();
        contentLayout.getChildren().addAll(navMenu, contentPane);
        contentLayout.setAlignment(Pos.TOP_CENTER);
        contentLayout.setSpacing(10);

        // Add sidebar to the left of the BorderPane
        dashboardContainer.setLeft(sidebar);

        // Add contentPane to the center
        dashboardContainer.setCenter(contentLayout);


        // Set up the scene
        this.scene = new Scene(dashboardContainer, 900, 600);
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-background-color: #4899dc; -fx-background-radius: 15px; -fx-text-fill: white; -fx-font-weight: 700;");
        button.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        return button;
    }

    private Button createExitButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-background-color: #ec5656; -fx-background-radius: 15px; -fx-text-fill: white; -fx-font-weight: 700;");
        button.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        return button;
    }

    private Button createNavButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(100, 40);
        button.setStyle("-fx-background-color: #4899dc; -fx-background-radius: 15px; -fx-text-fill: white; -fx-font-weight: 700;");
        button.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));
        return button;
    }

    public Scene getScene() {
        return scene;
    }
}
