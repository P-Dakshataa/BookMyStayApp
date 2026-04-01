import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CancellationService {

    private Stack<String> releasedRoomIds;


    private Map<String, String> reservationRoomTypeMap;


    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }


    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }


    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation request.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        releasedRoomIds.push(reservationId);

        Map<String, Integer> availability = inventory.getRoomAvailability();
        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        reservationRoomTypeMap.remove(reservationId);

        System.out.println(
                "Booking cancelled successfully. Inventory restored for room type: " + roomType
        );
    }


    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}