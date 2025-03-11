package Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ThankYouScreen {
    private Scene scene;

    public ThankYouScreen() {
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        StackPane thankYouContainer = new StackPane();
        thankYouContainer.setPrefSize(900, 600); // Adjust size as needed

        // Background Image
        ImageView backgroundImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/d4de7ada5a571e81779683e924dff48f944e8805"));
        backgroundImage.setFitWidth(900);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);

        // Overlay
        VBox overlay = new VBox();
        overlay.setStyle("-fx-background-color: rgba(95, 170, 231, 0.75);");
        overlay.setAlignment(Pos.CENTER);
        overlay.setPrefSize(900, 600);

        // Mountain Resort Logo
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/7a62240871dce4d0c4387ab9bdabaf92ad15d526"));
        logo.setFitWidth(100); // Adjust size as needed
        logo.setPreserveRatio(true);

        // Thank You Text
        Text thankYouText = new Text("Thank you !!");
        thankYouText.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 128)); // Adjust font size
        thankYouText.setFill(Color.WHITE);

        // Add components to overlay
        overlay.getChildren().addAll(logo, thankYouText);

        // Add background image and overlay to the main container
        thankYouContainer.getChildren().addAll(backgroundImage, overlay);

        // Set up the scene
        this.scene = new Scene(thankYouContainer, 900, 600);
    }

    public Scene getScene() {
        return scene;
    }
}