import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PaymentService {
    public double calculateTotalPrice(Room room, String checkInDate, String checkOutDate) {
        // Convert dates to LocalDate format
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);

        // Calculate number of nights
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights < 1) {
            nights = 1; // Minimum 1 night stay
        }

        return nights * room.getPrice(); // Total cost
    }

    public boolean processPayment(double amount) {
        System.out.println(" Processing payment of $" + amount + "...");
        System.out.println(" Payment successful!");
        return true; // Simulate successful payment
    }
}
