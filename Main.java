
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void clearConsole() {
        try {
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println(" Unable to clear screen.");
        }
    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<Booking> bookings = fileManager.loadBookingsFromCSV();

        RoomService roomService = new RoomService(fileManager);
        BookingService bookingService = new BookingService(bookings, roomService, fileManager);

        Scanner scanner = new Scanner(System.in);

        System.out.println(" /$$      /$$           /$$                                                   /$$ /$$");
        System.out.println("| $$  /$ | $$          | $$                                                  | $$| $$");
        System.out.println("| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$       | $$| $$");
        System.out.println("| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$      | $$| $$");
        System.out.println("| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$      |__/|__/");
        System.out.println("| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/              ");
        System.out.println("| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$       /$$ /$$");
        System.out.println("|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/      |__/|__/");

        while (true) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║       HOTEL BOOKING SYSTEM       ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║ 1. View Available Rooms          ║");
            System.out.println("║ 2. Book a Room                   ║");
            System.out.println("║ 3. View All Bookings             ║");
            System.out.println("║ 4. Cancel a Booking              ║");
            System.out.println("║ 5. Exit                          ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print(" Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    clearConsole();
                    System.out.println("\n Available Rooms:");
                    List<Room> availableRooms = roomService.getAvailableRooms();
                    if (availableRooms.isEmpty()) {
                        System.out.println(" No available rooms at the moment.");
                    } else {
                        for (Room room : availableRooms) {
                            System.out.println(" Room " + room.getId() + " - " + room.getType() + " - $" + room.getPrice() + " per night");
                        }
                    }
                }

                case 2 -> {
                    clearConsole();
                    System.out.print("\n Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print(" Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print(" Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print(" Enter check-in date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();
                    System.out.print(" Enter check-out date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.nextLine();

                    if (bookingService.bookRoom(name, email, roomNumber, checkInDate, checkOutDate)) {
                        fileManager.saveBookingsToCSV(bookings);
                    }
                }

                case 3 -> {
                    clearConsole();
                    System.out.println("\n╔═════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                            CURRENT BOOKINGS                                 ║");
                    System.out.println("╠═════════════════════════════════════════════════════════════════════════════╣");
                
                    if (bookings.isEmpty()) {
                        System.out.println("║                      No current bookings found.                  ║");
                    } else {
                        for (Booking b : bookings) {
                            System.out.printf("║ Room %-3d | Guest: %-10s | Check-in: %-10s | Check-out: %-10s ║\n",
                                b.getRoomNumber(), b.getName(), b.getCheckInDate(), b.getCheckOutDate());
                        }
                    }
                
                    System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
                    break;
                }

                case 4 -> {
                    clearConsole();
                    System.out.print("\n Enter room number to cancel booking: ");
                    int cancelRoomNumber = scanner.nextInt();
                    scanner.nextLine();
                    bookingService.cancelBooking(cancelRoomNumber);
                }

                case 5 -> {
                    clearConsole();
                    System.out.println(" /$$$$$$$$ /$$                           /$$                                                 /$$ /$$");
                    System.out.println("|__  $$__/| $$                          | $$                                                | $$| $$");
                    System.out.println("   | $$   | $$$$$$$   /$$$$$$  /$$$$$$$ | $$   /$$       /$$   /$$  /$$$$$$  /$$   /$$      | $$| $$");
                    System.out.println("   | $$   | $$__  $$ |____  $$| $$__  $$| $$  /$$/      | $$  | $$ /$$__  $$| $$  | $$      | $$| $$");
                    System.out.println("   | $$   | $$  \\ $$  /$$$$$$$| $$  \\ $$| $$$$$$/       | $$  | $$| $$  \\ $$| $$  | $$      |__/|__/");
                    System.out.println("   | $$   | $$  | $$ /$$__  $$| $$  | $$| $$_  $$       | $$  | $$| $$  | $$| $$  | $$              ");
                    System.out.println("   | $$   | $$  | $$|  $$$$$$$| $$  | $$| $$ \\  $$      |  $$$$$$$|  $$$$$$/|  $$$$$$/       /$$ /$$");
                    System.out.println("   |__/   |__/  |__/ \\_______/|__/  |__/|__/  \\__/       \\____  $$ \\______/  \\______/       |__/|__/");
                    System.out.println("                                                         /$$  | $$                                  ");
                    System.out.println("                                                        |  $$$$$$/                                  ");
                    System.out.println("                                                         \\______/   ");
                    System.out.println(" Thank you for using the Hotel Booking System!");
                    scanner.close();
                    return;
                }

                default -> {
                    clearConsole();
                    System.out.println(" Invalid choice. Please try again.");
                }

            }
        }
    }
}
