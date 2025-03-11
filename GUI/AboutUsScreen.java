package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AboutUsScreen {
    private Scene scene;

    public AboutUsScreen(MainController controller) {
        initializeUI(controller);
    }

    private void initializeUI(MainController controller) {
        // Main container
        StackPane developersSection = new StackPane();
        developersSection.setPrefSize(900, 600);

        // Background Image
        ImageView backgroundImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/8c8d1d2e9c56b45165fd95d0a6fbb0277518cd25"));
        backgroundImage.setFitWidth(900);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);

        // Overlay
        BorderPane developersOverlay = new BorderPane(); // Use BorderPane instead of VBox
        developersOverlay.setStyle("-fx-background-color: rgba(95, 170, 231, 0.75);");
        developersOverlay.setPrefSize(900, 600);

        // Developers Header
        HBox developersHeader = new HBox(16);
        developersHeader.setAlignment(Pos.CENTER);
        developersHeader.setPadding(new Insets(50, 0, 0, 0)); // Reduced padding

        // Developers Icon
        ImageView developersIcon = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/7a62240871dce4d0c4387ab9bdabaf92ad15d526"));
        developersIcon.setFitWidth(80); // Reduced size
        developersIcon.setFitHeight(80); // Reduced size
        developersIcon.setPreserveRatio(true);

        // Developers Title
        Text developersTitle = new Text("Developers");
        developersTitle.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 48)); // Reduced font size
        developersTitle.setFill(Color.WHITE);

        developersHeader.getChildren().addAll(developersIcon, developersTitle);

        // Developers List
        HBox developersList = new HBox(30); // Reduced spacing
        developersList.setAlignment(Pos.CENTER);
        developersList.setPadding(new Insets(30, 0, 0, 0)); // Reduced padding

        // Developer Cards
        VBox developerCard1 = createDeveloperCard(
                "https://cdn.builder.io/api/v1/image/assets/TEMP/b453e0885b78a8812573ccbdabbec58321ee8907",
                "Thy Sethasarakvath"
        );

        VBox developerCard2 = createDeveloperCard(
                "https://cdn.builder.io/api/v1/image/assets/TEMP/037df9fbcc067b11485fe5d8c1e69453e9b0fb14",
                "Nut Sophaphirum"
        );

        VBox developerCard3 = createDeveloperCard(
                "https://cdn.builder.io/api/v1/image/assets/TEMP/a8af00859158f8f2bc07c361a49eebfdbd5b8a79",
                "Hen Chhordavattey"
        );

        developersList.getChildren().addAll(developerCard1, developerCard2, developerCard3);

        // Center the developers list in the BorderPane
        VBox centerContent = new VBox(developersHeader, developersList);
        centerContent.setAlignment(Pos.TOP_CENTER);
        developersOverlay.setCenter(centerContent); // Place developers in the center

        // Back Button Container
        StackPane backButtonContainer = new StackPane();
        backButtonContainer.setAlignment(Pos.BOTTOM_CENTER);
        backButtonContainer.setPadding(new Insets(0, 0, 40, 0)); // Adjust bottom padding as needed

        // Back Button
        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 60); // Reduced size
        backButton.setStyle(
                "-fx-background-color: #4899dc; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            );
        backButton.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));

            // Hover Effect: Darken the button color slightly
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-background-color: #3a87c8; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            ));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: #4899dc; " + 
                "-fx-background-radius: 15px; " +
                "-fx-text-fill: white; " + 
                "-fx-font-weight: 700; " +
                "-fx-font-size: 14px;"
            ));

        // Back Button Action
        backButton.setOnAction(e -> controller.showDashboard()); // Navigate back to the dashboard

        backButtonContainer.getChildren().add(backButton);

        // Add the back button to the bottom of the BorderPane
        developersOverlay.setBottom(backButtonContainer); // Place the button at the bottom

        // Add background image and overlay to the main container
        developersSection.getChildren().addAll(backgroundImage, developersOverlay);

        // Set up the scene
        this.scene = new Scene(developersSection, 900, 600);
    }

    private VBox createDeveloperCard(String imageUrl, String developerName) {
        VBox developerCard = new VBox(10); // Reduced spacing
        developerCard.setAlignment(Pos.CENTER);

        // Developer Image
        ImageView developerImage = new ImageView(new Image(imageUrl));
        developerImage.setFitWidth(200); // Reduced size
        developerImage.setFitHeight(200); // Reduced size
        developerImage.setPreserveRatio(false);

        // Developer Name
        Text name = new Text(developerName);
        name.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24)); // Reduced font size
        name.setFill(Color.WHITE);

        developerCard.getChildren().addAll(developerImage, name);

        return developerCard;
    }

    public Scene getScene() {
        return scene;
    }
}