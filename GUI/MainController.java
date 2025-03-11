package Controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private Stage primaryStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showWelcomePage() {
        welcomepage welcomePage = new welcomepage(this);
        Scene scene = welcomePage.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();
    }

    public void showLoginPage() {
        Login loginPage = new Login(this);
        Scene scene = loginPage.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
    }

    public void showSignupPage() {
        Signup signupForm = new Signup(this);
        Scene scene = signupForm.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up");
    }
    
    public void showDashboard() {
        Dashboard dashboard = new Dashboard(this);
        Scene scene = dashboard.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }
    
    
}
