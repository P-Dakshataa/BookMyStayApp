import java.util.List;

public class BookingReportService {


    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        List<Reservation> reservations = history.getConfirmedReservations();

        for (Reservation reservation : reservations) {
            System.out.println(
                    "Guest: " + reservation.getGuestName() +
                            ", Room Type: " + reservation.getRoomType()
            );
        }
    }
}