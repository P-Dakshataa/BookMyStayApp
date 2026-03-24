import java.util.*;

public class RoomAllocationService {

    /*
     * Stores all allocated room IDs to prevent duplicates
     */
    private Set<String> allocatedRoomIds;

    /*
     * Stores assigned room IDs by room type
     * Key -> Room type
     * Value -> Set of room IDs
     */
    private Map<String, Set<String>> assignedRoomsByType;

    /*
     * Constructor
     */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /*
     * Allocates room for a reservation
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check availability
        if (availability.get(roomType) == null || availability.get(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        // Generate unique room ID
        String roomId = generateRoomId(roomType);

        // Store allocated ID
        allocatedRoomIds.add(roomId);

        // Track by type
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // Update inventory immediately
        inventory.updateAvailability(roomType, availability.get(roomType) - 1);

        // Confirmation
        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.getGuestName()
                        + ", Room ID: "
                        + roomId
        );
    }

    /*
     * Generates unique room ID
     */
    private String generateRoomId(String roomType) {

        int count = 1;

        // Ensure unique ID
        while (true) {
            String roomId = roomType + "-" + count;

            if (!allocatedRoomIds.contains(roomId)) {
                return roomId;
            }

            count++;
        }
    }
}
