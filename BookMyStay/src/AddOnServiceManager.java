public class AddOnServiceManager {


    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<Service> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }
}