import java.io.Serializable;

public class Booking implements Serializable {
    private final String name;
    private final String email;
    private final int roomNumber;
    private final String checkInDate;
    private final String checkOutDate;
    private final double totalPrice;

    public Booking(String name, String email, int roomNumber, String checkInDate, String checkOutDate , double totalPrice) {
        this.name = name;
        this.email = email;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    @Override
    public String toString() {
        return roomNumber + "," + name + "," + email + "," + checkInDate + "," + checkOutDate + "," + totalPrice;
    }
}
