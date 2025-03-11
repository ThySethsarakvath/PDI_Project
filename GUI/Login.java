package Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Login {
    private MainController controller;

    public Login(MainController controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        StackPane root = new StackPane();

        // Background Image
        Image backgroundImage = new Image("https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/56286b735793dc6ac7c241e95bb55b06f30620da765a42ae29315b6333a1487c?apiKey=b13786b9631c451597743fcb810395dc&");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        BorderPane root1 = new BorderPane();
        root1.setBackground(new Background(background));

        VBox contentWrapper = new VBox();
        contentWrapper.setPadding(new Insets(85, 52, 0, 52));
        contentWrapper.setStyle("-fx-background-color: rgba(95, 170, 231, 0.75);");
        contentWrapper.setSpacing(20);

        HBox loginGrid = new HBox(20);
        loginGrid.setAlignment(Pos.CENTER);

        // Left Side - Image
        ImageView loginImage = new ImageView(new Image(
                "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/d5e24dc897acbf6055ec94c29f039ea8e1806ab8db4020732b6cb5ba3c503d91?apiKey=b13786b9631c451597743fcb810395dc&"
        ));
        loginImage.setFitWidth(350);
        loginImage.setPreserveRatio(true);

        BorderPane imageBox = new BorderPane();
        imageBox.setBottom(loginImage);
        BorderPane.setAlignment(loginImage, Pos.CENTER);

        // Right Side - Form
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.TOP_LEFT);

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = new ImageView(new Image(
                "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/e5d013396e86fd805212d9f0245d3683d2b3dacb560e66000d90d4534b172dfd?apiKey=b13786b9631c451597743fcb810395dc&"
        ));
        logo.setFitWidth(80);
        logo.setPreserveRatio(true);

        Text loginTitle = new Text("Login");
        loginTitle.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 64));
        loginTitle.setFill(Color.BLUE);

        header.getChildren().addAll(logo, loginTitle);

        Text subtitle = new Text("Enter your Email and Password");
        subtitle.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24));
        subtitle.setFill(Color.WHITE);

        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24));
        emailLabel.setTextFill(Color.WHITE);

        TextField emailField = new TextField();
        emailField.setPromptText("Ex: vath@gmail.com");
        emailField.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'K2D'; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 15px;");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("K2D", FontWeight.EXTRA_BOLD, 24));
        passwordLabel.setTextFill(Color.WHITE);

        HBox passwordBox = new HBox();
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'K2D'; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 15px;");
        passwordField.setPrefWidth(250);

        ImageView eyeIcon = new ImageView(new Image(
                "https://cdn.builder.io/api/v1/image/assets/b13786b9631c451597743fcb810395dc/767d91c6e993f8d1642d20a34fd032c656869bf14b87dd6baec4aa5265bf6b50?apiKey=b13786b9631c451597743fcb810395dc&"
        ));
        eyeIcon.setFitWidth(24);
        eyeIcon.setPreserveRatio(true);

        eyeIcon.setOnMouseClicked(e -> {
            if (passwordField.getPromptText().equals("Enter your password")) {
                TextField tempField = new TextField(passwordField.getText());
                tempField.setPromptText("Enter your password");
                tempField.setStyle(passwordField.getStyle());
                passwordBox.getChildren().set(0, tempField);
            } else {
                passwordField.setText(((TextField) passwordBox.getChildren().get(0)).getText());
                passwordBox.getChildren().set(0, passwordField);
            }
        });

        passwordBox.getChildren().addAll(passwordField, eyeIcon);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        passwordBox.setSpacing(10);

        Button loginButton = new Button("Log In");
        loginButton.setStyle("-fx-background-color: #0096ff; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 15px 50px;");
        loginButton.setAlignment(Pos.CENTER);

        // Change style on hover
        loginButton.setOnMouseEntered(event -> {
            loginButton.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 15px 50px;");
        });

        loginButton.setOnMouseExited(event -> {
            loginButton.setStyle("-fx-background-color: #0096ff; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: 600; -fx-font-family: 'K2D'; -fx-background-radius: 19px; -fx-padding: 15px 50px;");
        });


        HBox signupBox = new HBox(5);
        Text signupText = new Text("Don't have an account?");
        signupText.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 14));
        signupText.setFill(Color.WHITE);

        Hyperlink signupLink = new Hyperlink("Sign up");
        signupLink.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 14));
        signupLink.setStyle("-fx-text-fill: #0033ff; -fx-underline: true;");
        signupLink.setOnAction(e -> controller.showSignupPage());


        signupBox.getChildren().addAll(signupText, signupLink);
        signupBox.setAlignment(Pos.CENTER_LEFT);

        formBox.getChildren().addAll(
                header, subtitle, emailLabel, emailField,
                passwordLabel, passwordBox,
                loginButton, signupBox
        );

        loginGrid.getChildren().addAll(imageBox, formBox);
        contentWrapper.getChildren().add(loginGrid);

        root1.setCenter(contentWrapper);

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (UserLogin.authenticateUser(email, password)) {
                System.out.println("Login successful! Redirecting...");
                controller.showDashboard();
                // Load hotel booking scene here
            } else {
                System.out.println("Invalid email or password.");
            }
        });


        root.getChildren().add(root1);
        return new Scene(root, 900, 600);
    }
}
