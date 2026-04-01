import java.util.Map;

public class ReservationValidator {


    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (roomType == null ||
                !(roomType.equals("Single") ||
                        roomType.equals("Double") ||
                        roomType.equals("Suite"))) {

            throw new InvalidBookingException("Invalid room type selected.");
        }

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) == null || availability.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}