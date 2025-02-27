import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private final List<Room> rooms;

    public RoomService(FileManager fileManager) {
        this.rooms = fileManager.loadRoomsFromCSV();
    }

    public Room findRoomById(int roomId) {
        return rooms.stream()
                .filter(room -> room.getId() == roomId)
                .findFirst()
                .orElse(null);
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream().filter(Room::isAvailable).collect(Collectors.toList());
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
}
