import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String BOOKINGS_FILE = "bookings.csv";
    private static final String ROOMS_FILE = "rooms.csv";

    // Save rooms to CSV (Not used often but useful for updating rooms)
    public void saveRoomsToCSV(List<Room> rooms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            writer.write("RoomNumber,Type"); // Header
            writer.newLine();
            for (Room r : rooms) {
                writer.write(r.getId() + "," + r.getType());
                writer.newLine();
            }
            System.out.println(" Rooms saved in rooms.csv");
        } catch (IOException e) {
            System.out.println(" Error saving rooms: " + e.getMessage());
        }
    }

    public void saveBookingsToCSV(List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
            writer.write("RoomNumber,Name,Email,CheckInDate,CheckOutDate"); // CSV Header
            writer.newLine();
            for (Booking b : bookings) {
                writer.write(b.getRoomNumber() + "," + b.getName() + "," + b.getEmail() + "," + b.getCheckInDate() + "," + b.getCheckOutDate());
                writer.newLine();
            }
            System.out.println(" Bookings saved in bookings.csv");
        } catch (IOException e) {
            System.out.println(" Error saving bookings: " + e.getMessage());
        }
    }
    

    // Load rooms from CSV
    public List<Room> loadRoomsFromCSV() {
        List<Room> rooms = new ArrayList<>();
        File file = new File("rooms.csv");
    
        if (!file.exists()) {
            System.out.println(" Warning: rooms.csv file not found! No rooms loaded.");
            return rooms;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
    
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { 
                    isFirstLine = false; // Skip CSV header
                    continue;
                }
    
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    rooms.add(new Room(
                        Integer.parseInt(parts[0]),  // Room Number
                        parts[1],                    // Type
                        Double.parseDouble(parts[2]) // Price
                    ));
                }
            }
    
            System.out.println(" Loaded " + rooms.size() + " rooms from CSV.");
        } catch (IOException e) {
            System.out.println(" Error loading rooms: " + e.getMessage());
        }
        return rooms;
    }
    

    public List<Booking> loadBookingsFromCSV() {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(BOOKINGS_FILE);
        
        if (!file.exists()) {
            System.out.println(" Warning: bookings.csv file not found! No bookings loaded.");
            return bookings;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { 
                    isFirstLine = false; // Skip CSV header
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    bookings.add(new Booking(parts[1], parts[2], Integer.parseInt(parts[0]), parts[3], parts[4], 0));
                }
            }

            System.out.println(" Loaded " + bookings.size() + " bookings from CSV.");
        } catch (IOException e) {
            System.out.println(" Error loading bookings: " + e.getMessage());
        }
        return bookings;
    }
}
