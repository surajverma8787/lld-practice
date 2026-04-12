import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Movie movie1 = new Movie("Inception");

        // Create Seats
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            seats.add(new Seat(i, SeatCategory.SILVER));
        }

        // Create Screen
        Screen screen1 = new Screen(1, seats);

        // Create Show
        LocalDate today = LocalDate.now();
        Show show1 = new Show(movie1, screen1, today, LocalTime.of(18, 0));

        // Add show to screen
        screen1.addShow(show1);

        // Create Theatre
        Theatre theatre = new Theatre("PVR", City.BANGALORE, List.of(screen1));

        // Theatre Controller
        TheatreController theatreController = new TheatreController();
        theatreController.addTheatre(theatre);

        // Create User
        User user = new User("U1", "Suraj");

        //  Booking Controller
        BookingController bookingController = new BookingController();

        // Get Movies in City
        System.out.println("Movies in Bangalore:");
        Set<Movie> movies = theatreController.getMovies(City.BANGALORE, today);
        for (Movie m : movies) {
            System.out.println(m.getName());
        }

        // Get Theatres for Movie
        System.out.println("\nTheatres showing Inception:");
        List<Theatre> theatres = theatreController.getTheatres(City.BANGALORE, movie1, today);
        for (Theatre t : theatres) {
            System.out.println(t.getName());
        }

        // Step 3: Get Shows
        System.out.println("\nShows:");
        List<Show> shows = theatreController.getShows(movie1, today, theatre);
        for (Show s : shows) {
            System.out.println(s.getStartTime());
        }

        // Book Seats
        List<Integer> seatsToBook = List.of(1, 2, 3);

        try {
            Booking booking = bookingController.createBooking(user, show1, seatsToBook);
            System.out.println("\nBooking Successful!");
            System.out.println("Booking ID: " + booking.getBookingId());
        } catch (Exception e) {
            System.out.println("\nBooking Failed: " + e.getMessage());
        }

        // Try booking same seats again (should fail)
        try {
            bookingController.createBooking(user, show1, seatsToBook);
        } catch (Exception e) {
            System.out.println("\nSecond booking failed (expected): " + e.getMessage());
        }
    }
}
