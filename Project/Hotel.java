import java.util.List;

public class Hotel {
    private final String name;
    private final List<Room> rooms;

    public Hotel(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
