public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        // Step 1: Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Step 2: Initialize booking queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Step 3: Add booking requests (FIFO)
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Step 4: Allocation service
        RoomAllocationService allocationService = new RoomAllocationService();

        // Step 5: Process queue
        while (queue.hasPendingRequests()) {

            Reservation request = queue.getNextRequest();

            allocationService.allocateRoom(request, inventory);
        }
    }
}
