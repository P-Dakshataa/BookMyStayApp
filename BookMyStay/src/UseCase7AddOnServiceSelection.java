public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

=        String reservationId = "Single-1";

=        AddOnServiceManager manager = new AddOnServiceManager();

=        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1000);

=        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

=        double totalCost = manager.calculateTotalServiceCost(reservationId);

=        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}

