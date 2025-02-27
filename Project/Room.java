public class Room {
    private final int id;
    private final String type;
    private boolean isBooked;
    private final double price;
    
        public Room(int id, String type , double price) {
            this.id = id;
            this.type = type;
            this.price = price;
            this.isBooked = false; // Default: Room is available
        }
    
        public int getId() {
            return id;
        }
    
        public String getType() {
            return type;
        }
    
        public boolean isBooked() {
            return isBooked;
        }
    
        public void setBooked(boolean booked) {
            this.isBooked = booked;
    }

    public boolean isAvailable() {
        return !isBooked;
    }
    public double getPrice() {
        return price;
    }
}
