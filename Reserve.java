import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

interface Reserve {
    void initializeTickets(Ticket[] tck);
    void serializeTickets(Ticket[] tickets);
    int[] seatAvailability(Scanner sc);
    boolean processCreditCardPayment(Scanner sc);
    String getTravelDate(Scanner sc, String presentDate, String nextDate, DateTimeFormatter df);
}

