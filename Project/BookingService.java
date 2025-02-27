import java.util.List;

public class BookingService {
    private final List<Booking> bookings;
    private final RoomService roomService;
    private final FileManager fileManager;

    public BookingService(List<Booking> bookings, RoomService roomService, FileManager fileManager) {
        this.bookings = bookings;
        this.roomService = roomService;
        this.fileManager = fileManager;
    }

    public boolean bookRoom(String name, String email, int roomNumber, String checkInDate, String checkOutDate) {
        Room selectedRoom = roomService.findRoomById(roomNumber);
        
        if (selectedRoom == null) {
            System.out.println(" Error: Room number " + roomNumber + " does not exist.");
            return false;
        }
        if (selectedRoom.isBooked()) {
            System.out.println(" Error: Room " + roomNumber + " is already booked.");
            return false;
        }
    
        //  Calculate total price
        PaymentService paymentService = new PaymentService();
        double totalPrice = paymentService.calculateTotalPrice(selectedRoom, checkInDate, checkOutDate);
    
        //  Process Payment
        if (!paymentService.processPayment(totalPrice)) {
            System.out.println(" Payment failed. Booking cancelled.");
            return false;
        }
    
        //  Create booking after successful payment
        Booking newBooking = new Booking(name, email, roomNumber, checkInDate, checkOutDate, totalPrice);
        bookings.add(newBooking);
        selectedRoom.setBooked(true);
        fileManager.saveBookingsToCSV(bookings); //  Save to CSV
    
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                 PAYMENT SUCCESSFUL               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║  Room: %-3d             |  Total: $%-6.2f         ║\n", roomNumber, totalPrice);
        System.out.printf("║  Check-in: %-10s  |  Check-out: %-10s  ║\n", checkInDate, checkOutDate);
        System.out.println("╚══════════════════════════════════════════════════╝");
        return true;
    }
    

    public void cancelBooking(int roomNumber) {
        Booking bookingToRemove = null;
        
        for (Booking b : bookings) {
            if (b.getRoomNumber() == roomNumber) {
                bookingToRemove = b;
                break;
            }
        }

        if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
            Room room = roomService.findRoomById(roomNumber);
            if (room != null) {
                room.setBooked(false);
            }
            fileManager.saveBookingsToCSV(bookings); // Save updated bookings
            System.out.println(" Booking for Room " + roomNumber + " has been canceled.");
        } else {
            System.out.println(" No booking found for Room " + roomNumber + ".");
        }
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}
