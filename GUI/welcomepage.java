package Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class welcomepage {
    private MainController controller;

    public welcomepage(MainController controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        // Background Image
        ImageView backgroundImage = new ImageView(new Image(
                "https://cdn.builder.io/api/v1/image/assets/TEMP/8c8d1d2e9c56b45165fd95d0a6fbb0277518cd25"));
        backgroundImage.setFitWidth(1000);
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);

        StackPane.setAlignment(backgroundImage, Pos.CENTER);

        // Overlay
        Region overlay = new Region();
        overlay.setStyle("-fx-background-color: rgba(95, 170, 231, 0.5);");
        overlay.setPrefSize(1000, 600);

        // Welcome Text
        Label welcomeText = new Label("WELCOME !!!");
        welcomeText.setTextFill(Color.WHITE);
        welcomeText.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 128));

        // Hotel Logo
        ImageView hotelLogo = new ImageView(new Image(
                "https://cdn.builder.io/api/v1/image/assets/TEMP/bd3b38805d3ea264a648ef1dc93f27537e7019e4"));
        hotelLogo.setFitWidth(122);
        hotelLogo.setFitHeight(122);

        // Hotel Name
        Label hotelName = new Label("Angkor Blossom Hotel");
        hotelName.setTextFill(Color.WHITE);
        hotelName.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 64));

        // Hotel Info Box
        HBox hotelInfoBox = new HBox(30, hotelLogo, hotelName);
        hotelInfoBox.setAlignment(Pos.CENTER);

        // Start Button
        Button startButton = new Button("Get Start");
        startButton.setTextFill(Color.WHITE);
        startButton.setStyle("-fx-background-color: #3093e5; -fx-border-color: #7bb9de; -fx-border-width: 5px; -fx-font-size: 32px;");

        // Change style on hover
        startButton.setOnMouseEntered(event -> {
            startButton.setStyle("-fx-background-color: #1e74b3; -fx-border-color: #6ea8d4; -fx-border-width: 5px; -fx-font-size: 32px;");
        });

        startButton.setOnMouseExited(event -> {
            startButton.setStyle("-fx-background-color: #3093e5; -fx-border-color: #7bb9de; -fx-border-width: 5px; -fx-font-size: 32px;");
        });

        // Add the action for the button
        startButton.setOnAction(e -> controller.showLoginPage());

        VBox content = new VBox(30, welcomeText, hotelInfoBox, startButton);
        content.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(backgroundImage, overlay, content);
        root.setStyle("-fx-background-color: black;");

        return new Scene(root, 1000, 600);
    }
}
