package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.regex.Pattern;

public class Signup {
    private MainController controller;
    private Scene scene;

    public Signup(MainController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        // Main container
        VBox signupContainer = new VBox();
        signupContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        signupContainer.setMaxWidth(900);
        signupContainer.setAlignment(Pos.CENTER);

        // Signup Wrapper
        StackPane signupWrapper = new StackPane();
        signupWrapper.setMinHeight(600);
        signupWrapper.setPrefWidth(900);

        // Background Image
        ImageView backgroundImage = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/f53eb2a0d0cfe97fbccdb882761d1856a44051b3f5dada71ed01611077b4eeb3?apiKey=b13786b9631c451597743fcb810395dc&"));
        backgroundImage.setFitWidth(900);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);

        // Overlay
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(95, 170, 231, 0.75);");
        overlay.setPrefSize(900, 600);

        // Content Wrapper
        VBox contentWrapper = new VBox();
        contentWrapper.setPadding(new Insets(59, 25, 0, 25));
        contentWrapper.setAlignment(Pos.TOP_CENTER);

        // Two-Column Layout
        HBox twoColumnLayout = new HBox(20);
        twoColumnLayout.setAlignment(Pos.CENTER);

        // Left Column
        VBox leftColumn = new VBox();
        leftColumn.setAlignment(Pos.CENTER);

        // Illustration
        ImageView illustration = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/771d366ec49514528c845d724a25009ad6900533d3541c655c6c2b6ec93661eb?apiKey=b13786b9631c451597743fcb810395dc&"));
        illustration.setFitWidth(400);
        illustration.setFitHeight(500);
        leftColumn.getChildren().add(illustration);

        // Right Column
        VBox rightColumn = new VBox();
        rightColumn.setAlignment(Pos.TOP_LEFT);

        // Signup Form
        VBox signupForm = new VBox(10); // Reduced spacing
        signupForm.setAlignment(Pos.TOP_LEFT);

        // Header
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        // Logo
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/aeb5e7eb592a7feaf7e25fd80f6b3f23dc7d064d25d518fb49eb6a81336020b9?apiKey=b13786b9631c451597743fcb810395dc&"));
        logo.setFitWidth(60);
        logo.setFitHeight(60);

        // Signup Title
        Label signupTitle = new Label("Sign up");
        signupTitle.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: 800;");
        signupTitle.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 40));

        header.getChildren().addAll(logo, signupTitle);

        // Signup Subtitle
        Label signupSubtitle = new Label("Enter your credentials to continue");
        signupSubtitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");
        signupSubtitle.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 16));

        // Form Fields
        HBox formFields = new HBox(50);
        formFields.setAlignment(Pos.TOP_LEFT);

        // Left Fields
        VBox leftFields = new VBox(5); // Reduced spacing
        leftFields.setAlignment(Pos.TOP_LEFT);

        // Username Label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");

        // Username Input
        TextField usernameInput = new TextField();
        usernameInput.setPrefSize(175, 38);
        usernameInput.setStyle("-fx-background-radius: 15px; -fx-font-size: 16px;");

        leftFields.getChildren().addAll(usernameLabel, usernameInput);

        // Right Fields
        VBox rightFields = new VBox(5); // Reduced spacing
        rightFields.setAlignment(Pos.TOP_LEFT);

        // Phone Number Label
        Label phoneLabel = new Label("Phone Number:");
        phoneLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");

        // Phone Input
        TextField phoneInput = new TextField();
        phoneInput.setPrefSize(175, 38);
        phoneInput.setStyle("-fx-background-radius: 15px; -fx-font-size: 16px;");

        rightFields.getChildren().addAll(phoneLabel, phoneInput);

        formFields.getChildren().addAll(leftFields, rightFields);

        // Email Input
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");

        TextField emailInput = new TextField();
        emailInput.setPrefSize(390, 38);
        emailInput.setPromptText("Ex: vath@gmail.com");
        emailInput.setStyle("-fx-background-radius: 15px; -fx-font-size: 16px;");

        // Password Label
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");

        // Password Input
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(390, 41);
        passwordField.setStyle("-fx-background-radius: 15px; -fx-font-size: 16px;");

        // Confirm Password Label
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 800;");

        // Confirm Password Input
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefSize(390, 41);
        confirmPasswordField.setStyle("-fx-background-radius: 15px; -fx-font-size: 16px;");

        // Show Password Toggle
        HBox passwordToggleBox = new HBox(10);
        passwordToggleBox.setAlignment(Pos.CENTER_LEFT);

        Button showPasswordButton = new Button("Show Password");
        showPasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #007bff; -fx-font-size: 14px; -fx-underline: true;");

        showPasswordButton.setOnAction(e -> {
            if (passwordField.getPromptText().equals("Enter your password")) {
                TextField tempField = new TextField(passwordField.getText());
                tempField.setPromptText("Enter your password");
                tempField.setStyle(passwordField.getStyle());
                passwordField.setVisible(false);
                passwordToggleBox.getChildren().set(0, tempField);
            } else {
                passwordField.setText(((TextField) passwordToggleBox.getChildren().get(0)).getText());
                passwordField.setVisible(true);
                passwordToggleBox.getChildren().set(0, passwordField);
            }
        });

        passwordToggleBox.getChildren().add(passwordField);

        // Terms Text
        Label termsText = new Label("By continuing you agree to our Terms of Service and Privacy Policy.");
        termsText.setStyle("-fx-text-fill: #030303; -fx-font-size: 10px;");
        termsText.setFont(Font.font("Montserrat", 10));

        // Signup Button
        Button signupButton = new Button("Sign Up");
        signupButton.setStyle("-fx-background-color: #0096ff; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 10px 35px;");
        signupButton.setAlignment(Pos.CENTER);

        // Change style on hover
        signupButton.setOnMouseEntered(event -> {
            signupButton.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 10px 35px;");
        });

        signupButton.setOnMouseExited(event -> {
            signupButton.setStyle("-fx-background-color: #0096ff; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 10px 35px;");
        });

        VBox buttonWrapper = new VBox();
        buttonWrapper.setAlignment(Pos.CENTER);  // This centers the button vertically
        buttonWrapper.setSpacing(20);  // Adjust spacing if necessary
        buttonWrapper.getChildren().add(signupButton);

        // Login Section
        HBox loginSection = new HBox(6);
        loginSection.setAlignment(Pos.CENTER);

        Label loginText = new Label("Already have an account?");
        loginText.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 14));
        loginText.setStyle("-fx-text-fill: white; -fx-font-size: 10px;");

        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 14));
        loginLink.setStyle("-fx-text-fill: #0033FF; -fx-font-size: 10px; -fx-underline: true;");

        // Set login button action
        loginLink.setOnAction(e -> controller.showLoginPage());

        loginSection.getChildren().addAll(loginText, loginLink);

        // Add all components to the form
        signupForm.getChildren().addAll(header, signupSubtitle, formFields, emailLabel, emailInput, passwordLabel, passwordToggleBox, confirmPasswordLabel, confirmPasswordField, termsText, signupButton, loginSection);

        // Add left and right columns to the layout
        twoColumnLayout.getChildren().addAll(leftColumn, signupForm);

        rightColumn.getChildren().add(buttonWrapper);

        // Add content to the wrapper
        contentWrapper.getChildren().add(twoColumnLayout);

        // Add all layers to the main container
        signupWrapper.getChildren().addAll(backgroundImage, overlay, contentWrapper);
        signupContainer.getChildren().add(signupWrapper);

        signupButton.setOnAction(e -> {
            String username = usernameInput.getText().trim();
            String email = emailInput.getText().trim();
            String phone = phoneInput.getText().trim();
            String password = passwordField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();

            // Validate inputs
            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
            } else if (!isValidEmail(email)) {
                showAlert("Input Error", "Invalid email format. Please use the format 'example@gmail.com'.");
            } else if (!isValidPhone(phone)) {
                showAlert("Input Error", "Invalid phone number. Please enter a valid phone number.");
            } else if (!password.equals(confirmPassword)) {
                showAlert("Input Error", "Passwords do not match.");
            } else if (UserRegistration.registerUser(username, email, phone, password)) {
                showAlert("Signup Successful", "You have successfully signed up!");
                controller.setLoggedInUserEmail(email); // Set the logged-in user's email
                controller.showDashboard(); // Navigate to the dashboard
            } else {
                showAlert("Signup Failed", "Signup failed. Email may already exist.");
            }
        });

        // Set up the scene
        this.scene = new Scene(signupContainer, 900, 600);
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Helper method to validate phone number format
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{10}$"; // Assumes a 10-digit phone number
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}