package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Database.dataBase;

public class ProfileScreen {
    private Scene scene;
    private MainController controller;

    public ProfileScreen(MainController controller) {
        this.controller = controller;
        initializeUI(controller);
    }

    private void initializeUI(MainController controller) {
        // Main container
        HBox profileContainer = new HBox();
        profileContainer.setPrefSize(900, 600);
        profileContainer.setStyle("-fx-background-color: #4798db;");

        // Sidebar
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(280);
        sidebar.setStyle("-fx-background-color: white;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20));

        // Logo
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/94b87c9494afcb567318adb1f328134d0487e120"));
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
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
        backButton.setOnAction(e -> controller.showDashboard());

        // Spacer to push back button to the bottom
        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        // Add components to the sidebar
        sidebar.getChildren().addAll(logo, sidebarSpacer, backButton);
        VBox.setMargin(logo, new Insets(60, 0, 40, 0));
        VBox.setMargin(backButton, new Insets(0, 0, 30, 0)); // Moves the button up by 30px

        // Main Content
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(50, 50, 50, 50));
        mainContent.setSpacing(20);

        // Profile Image
        ImageView profileImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/276c33f483e5d091d886f345661d5ce2161b98e4"));
        profileImage.setFitWidth(120);
        profileImage.setFitHeight(120);
        profileImage.setPreserveRatio(true);

        // Form
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        form.setSpacing(30);

        // Fetch user data from the database
        String[] userData = fetchUserData(controller.getLoggedInUserEmail());

        // Username, Phone, and Email Fields
        HBox usernameField = createDisplayField("Username:", userData[0]);
        HBox phoneField = createDisplayField("Phone:", userData[1]);
        HBox emailField = createDisplayField("Email:", userData[2]);

        // Add components to the form
        form.getChildren().addAll(usernameField, phoneField, emailField);

        // Spacer to push log-out button down
        Region mainSpacer = new Region();
        VBox.setVgrow(mainSpacer, Priority.ALWAYS);

        // Logout Button
        Button logoutButton = new Button("Log out");
        logoutButton.setPrefSize(200, 50);
        logoutButton.setStyle(
            "-fx-background-color: #ec5656; " +
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700;"
        );
        logoutButton.setFont(Font.font("Montserrat", FontWeight.BOLD, 14));

        // Hover Effect: Darken the button color slightly
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(
            "-fx-background-color: #d94444; " +
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle(
            "-fx-background-color: #ec5656; " +
            "-fx-background-radius: 15px; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 700; " +
            "-fx-font-size: 14px;"
        ));
        logoutButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText("Are you sure you want to log out?");
            alert.setContentText("Your session will be ended.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                controller.setLoggedInUserEmail(null); // Clear the logged-in user's email
                controller.showLoginPage(); // Proceed with logout
            }
        });

        // Add components to the main content
        mainContent.getChildren().addAll(profileImage, form, mainSpacer, logoutButton);

        // Add sidebar and main content to the profile container
        profileContainer.getChildren().addAll(sidebar, mainContent);

        // Set up the scene
        this.scene = new Scene(profileContainer, 900, 600);
    }

    private HBox createDisplayField(String labelText, String value) {
        HBox formField = new HBox();
        formField.setAlignment(Pos.CENTER_LEFT);
        formField.setSpacing(15);

        // Label
        Label label = new Label(labelText);
        label.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24));
        label.setTextFill(Color.WHITE);
        label.setPrefWidth(150);

        // Value Field
        Label valueField = new Label(value);
        valueField.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24));
        valueField.setTextFill(Color.WHITE);

        formField.getChildren().addAll(label, valueField);
        return formField;
    }

    private String[] fetchUserData(String email) {
        String[] userData = new String[3]; // username, phone, email
        String sql = "SELECT username, phone_number, email FROM users WHERE email = ?";

        try (Connection conn = dataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userData[0] = rs.getString("username");
                userData[1] = rs.getString("phone_number");
                userData[2] = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public Scene getScene() {
        return scene;
    }
}