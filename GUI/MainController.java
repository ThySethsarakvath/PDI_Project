package Controller;

import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private Stage primaryStage;
    private String loggedInUserEmail;
    private ViewBookingScreen viewBookingScreen; // Reference to the ViewBookingScreen

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
    }

    public String getLoggedInUserEmail() {
        return loggedInUserEmail;
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
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void showAboutUsPage() {
        AboutUsScreen aboutUsScreen = new AboutUsScreen(this);
        Scene scene = aboutUsScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("About Us");
        primaryStage.show();
    }
    
    public void showprofile() {
        ProfileScreen profile = new ProfileScreen(this);
        Scene scene = profile.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile");
        primaryStage.show();
    }
    
    public void showViewAllRooms() {
        ViewAllRooms viewAllRooms = new ViewAllRooms(this);
        Scene scene = viewAllRooms.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("View All Rooms");
        primaryStage.show();
    }
    
    public void showViewAllRoomsSix() {
        ViewAllRoomsSix viewAllRoomsSix = new ViewAllRoomsSix(this);
        Scene scene = viewAllRoomsSix.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("View All Rooms (Six)");
        primaryStage.show();
    }
    
    public void showRoomDetails(String roomTitle, String roomPrice, String roomImageUrl) {
        RoomDetailsScreen roomDetailsScreen = new RoomDetailsScreen(this, roomTitle, roomPrice, roomImageUrl);
        Scene scene = roomDetailsScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room Details");
        primaryStage.show();
    }
    
    public void showBookingConfirmation(String roomTitle, String roomImageUrl, LocalDate checkInDate, LocalDate checkOutDate, long numDays, double totalPrice) {
        BookingConfirmationScreen confirmationScreen = new BookingConfirmationScreen(this, roomTitle, roomImageUrl, checkInDate, checkOutDate, numDays, totalPrice);
        Scene scene = confirmationScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Booking Confirmation");
        primaryStage.show();
    }
    
    public void showViewBooking() {
        viewBookingScreen = new ViewBookingScreen(this); // Initialize or refresh the ViewBookingScreen
        Scene scene = viewBookingScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Booking");
        primaryStage.show();
    }
    
    public void showCancelBooking() {
        CancelBookingScreen cancelBookingScreen = new CancelBookingScreen(this);
        Scene scene = cancelBookingScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cancel Booking");
        primaryStage.show();
    }

    // Method to refresh the ViewBookingScreen
    public void refreshViewBookingScreen() {
        if (viewBookingScreen != null) {
            viewBookingScreen.initializeUI(); // Reinitialize the UI to refresh the table
        }
    }
}