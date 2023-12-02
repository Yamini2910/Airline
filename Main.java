
import java.util.Scanner;

public class Main {
    static Route[] intlFlights = new Route[5];
    Ticket[] tickets = new Ticket[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AirlineTicketSystem airlineSystem = new AirlineTicketSystem();

        Ticket[] savedTickets = airlineSystem.deserializeTickets();
        Ticket[] tck = (savedTickets != null) ? savedTickets : new Ticket[10];

        int op = 1;

        do {
            System.out.println("1. Domestic Flight Booking\t\t2. International Flight Booking\t\t0. Exit");
            System.out.print("Enter your choice (0-2): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DomesticFlightBooking.bookDomesticFlight(sc, new Aircraft[5], new Route[20], 20, new Ticket[10], intlFlights);
                    break;
                case 2:
                    InternationalFlightBooking.bookInternationalFlight(sc, new Aircraft[5], new Route[20], 20, new Ticket[10], intlFlights);
                    break;
                case 0:
                    System.out.println("Exiting the Airline Ticket Reservation System.");
                    airlineSystem.serializeTickets(tck);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 2.");
                    break;
            }

            System.out.println("\nDo you want to continue? Press 1 for 'yes' and 0 for 'no': ");
            op = sc.nextInt();
            sc.nextLine();

        } while (op != 0);

        System.out.println("\nExiting the Airline Ticket Reservation System.");
        airlineSystem.serializeTickets(tck);
    }
}

