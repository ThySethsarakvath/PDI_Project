package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewBookingScreen {
    private Scene scene;
    private MainController controller;

    public ViewBookingScreen(MainController controller) {
        this.controller = controller;
        initializeUI();
    }

    void initializeUI() {
        // Main container
        VBox viewBookingContainer = new VBox();
        viewBookingContainer.setAlignment(Pos.TOP_CENTER);
        viewBookingContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        viewBookingContainer.setPrefSize(900, 600); // Fixed size for the screen

        // Header background (top bar) - Increased height
        Pane headerBackground = new Pane();
        headerBackground.setPrefSize(900, 120); // Increased height of the top bar
        headerBackground.setBackground(new Background(new BackgroundFill(Color.web("#4798db"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Logo container (inside the top bar) - Adjusted for increased height
        HBox logoContainer = new HBox();
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setPrefSize(900, 120); // Same height as the header

        // Load the logo image
        ImageView logo = new ImageView(new Image("https://cdn.builder.io/api/v1/image/assets/TEMP/94b87c9494afcb567318adb1f328134d0487e120"));
//        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(100); // Smaller logo size
        logo.setPreserveRatio(true);
        logoContainer.getChildren().add(logo);

        // Back button (inside the top bar) - Created programmatically
        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.showDashboard());

        // Create a back arrow shape using Path
        Path backArrow = new Path();
        backArrow.setStroke(Color.WHITE); // Arrow color
        backArrow.setStrokeWidth(2); // Arrow thickness

        // Define the arrow shape
        backArrow.getElements().addAll(
                new MoveTo(15, 10), // Start at the tip of the arrow
                new LineTo(5, 15), // Draw to the left
                new LineTo(15, 20) // Draw back to the bottom
        );

        // Set the arrow as the graphic for the back button
        backButton.setGraphic(backArrow);

        // Position the back button at the top-left corner of the top bar
        StackPane headerPane = new StackPane();
        headerPane.setPrefSize(900, 120); // Same height as the header
        headerPane.getChildren().addAll(headerBackground, logoContainer, backButton);
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT); // Align back button to the left
        StackPane.setMargin(backButton, new Insets(-10, 0, 0, 20)); // Move up by 10px

        // Main content container
        VBox mainContent = new VBox(20); // Gap between elements
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20)); // Padding inside the container

        // Table to display bookings
        TableView<Booking> bookingTable = new TableView<>();
        bookingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Auto-resize columns
        bookingTable.setPrefHeight(400); // Set table height

        // Columns for the table
        TableColumn<Booking, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        TableColumn<Booking, LocalDate> checkInColumn = new TableColumn<>("Check-in Date");
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));

        TableColumn<Booking, LocalDate> checkOutColumn = new TableColumn<>("Check-out Date");
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        TableColumn<Booking, Double> pricePerNightColumn = new TableColumn<>("Price Per Night");
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

        TableColumn<Booking, Integer> numDaysColumn = new TableColumn<>("Number of Days");
        numDaysColumn.setCellValueFactory(new PropertyValueFactory<>("numDays"));

        TableColumn<Booking, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Add columns to the table
        bookingTable.getColumns().addAll(roomTypeColumn, checkInColumn, checkOutColumn, pricePerNightColumn, numDaysColumn, totalPriceColumn);

        // Fetch bookings for the logged-in user
        ObservableList<Booking> bookings = fetchBookings(controller.getLoggedInUserEmail());
        bookingTable.setItems(bookings);

        // Add table to the main content
        mainContent.getChildren().add(bookingTable);

        // Add all components to the main container
        viewBookingContainer.getChildren().addAll(headerPane, mainContent);

        // Set up the scene
        this.scene = new Scene(viewBookingContainer, 900, 600); // Fixed size for the screen
    }

    // Fetch bookings for the logged-in user
    private ObservableList<Booking> fetchBookings(String userEmail) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        String sql = "SELECT room_type, check_in_date, check_out_date, price_per_night, num_days, total_price " +
                     "FROM bookings WHERE user_id = (SELECT id FROM users WHERE email = ?)";

        try (Connection conn = Database.dataBase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String roomType = rs.getString("room_type");
                LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
                LocalDate checkOutDate = rs.getDate("check_out_date").toLocalDate();
                double pricePerNight = rs.getDouble("price_per_night");
                int numDays = rs.getInt("num_days");
                double totalPrice = rs.getDouble("total_price");

                bookings.add(new Booking(roomType, checkInDate, checkOutDate, pricePerNight, numDays, totalPrice));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public Scene getScene() {
        return scene;
    }

    // Booking class to represent a booking record
    public static class Booking {
        private String roomType;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private double pricePerNight;
        private int numDays;
        private double totalPrice;

        public Booking(String roomType, LocalDate checkInDate, LocalDate checkOutDate, double pricePerNight, int numDays, double totalPrice) {
            this.roomType = roomType;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.pricePerNight = pricePerNight;
            this.numDays = numDays;
            this.totalPrice = totalPrice;
        }

        // Getters for table columns
        public String getRoomType() { return roomType; }
        public LocalDate getCheckInDate() { return checkInDate; }
        public LocalDate getCheckOutDate() { return checkOutDate; }
        public double getPricePerNight() { return pricePerNight; }
        public int getNumDays() { return numDays; }
        public double getTotalPrice() { return totalPrice; }
    }
}