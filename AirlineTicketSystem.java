import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeParseException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;  // Add this import statement
import java.io.ObjectInputStream;
import java.io.FileNotFoundException; 
import java.io.Serializable;

class AirlineTicketSystem implements Reserve, Serializable {
    private static int lastAssignedTicketId = 0;

    public void initializeTickets(Ticket[] tck) {
        Ticket[] deserializedTickets = deserializeTickets();

        if (deserializedTickets != null && deserializedTickets.length > 0) {
            // If there are deserialized tickets, set the last assigned ticket ID
            lastAssignedTicketId = deserializedTickets[deserializedTickets.length - 1].getTicketId();
        }

        for (int i = 0; i < 10; i++) {
            tck[i] = new Ticket();
        }
    }

    public void serializeTickets(Ticket[] tickets) {
        // Update the last assigned ticket ID before serializing
        lastAssignedTicketId = tickets[tickets.length - 1].getTicketId();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tickets.ser"))) {
            oos.writeObject(tickets);
            System.out.println("Ticket information serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ticket[] deserializeTickets() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tickets.ser"))) {
            return (Ticket[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No serialized data found. Returning null.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }




    public void initializeAircrafts(Aircraft[] acft) {
        for (int i = 0; i < 5; i++)
            acft[i] = new Aircraft();

        acft[0].setdetails("Airbus A320-200", "passenger", 180, 730);
        acft[1].setdetails("Boeing 737-700", "passenger", 132, 510);
        acft[2].setdetails("Airbus A330-900neo", "passenger", 210, 650);
        acft[3].setdetails("Boeing 737-900", "passenger", 140, 580);
        acft[4].setdetails("Airbus A340-300F", "passenger", 126, 850);
    }

    public static int generateRandomNumber(int maxNumber) {
        Random random = new Random();
        return random.nextInt(maxNumber+1);
    }

    public static int generateRandomNumber(int minNumber, int maxNumber) {
        Random random = new Random();
        return minNumber + random.nextInt(maxNumber-minNumber+1);
    }

    public static int[] generateRandomArray(int totalRoutes, int minNumber, int maxNumber) {
        int[] routeCodes = new int[totalRoutes];
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < totalRoutes) {
            int randomNumber = generateRandomNumber(minNumber, maxNumber);
            uniqueNumbers.add(randomNumber);
        }

        int index = 0;
        for (int number : uniqueNumbers) {
            routeCodes[index] = number;
            index++;
        }
        return routeCodes;
    }

    public  void initializeFlightRoutes(Route[] flt, int totalRoutes, String[] city){
        String[] trafficTypes = {"low", "busy", "moderate"};
        int[] routeCodes = generateRandomArray(totalRoutes, 999, 9999);
        int[] sourceAndDestIndexPair;
        int trafficType, srcIndex, destIndex, flightFare, flightFrequency;
        String formattedRandomTime;

        for (int i = 0; i < totalRoutes; i++) {
            trafficType = generateRandomNumber(trafficTypes.length-1);
            flightFare = generateRandomNumber(1200, 15000);
            flightFrequency =  generateRandomNumber(2, 8);
            sourceAndDestIndexPair = generateRandomArray(2, 0, city.length-1);
            srcIndex = sourceAndDestIndexPair[0];
            destIndex = sourceAndDestIndexPair[1];
            formattedRandomTime = String.format("%02d:%02d:00", generateRandomNumber(24), generateRandomNumber(60));

            flt[i] = new Route();
            flt[i].setroute(trafficTypes[trafficType], routeCodes[i], city[srcIndex], city[destIndex], formattedRandomTime, flightFare, flightFrequency);
        }
    }
    public void initializeInternationalFlights(Route[] intlFlights) {
        String[] trafficTypes = {"low", "busy", "moderate"};
        String[] intlCities = {"New York", "London", "Tokyo", "Dubai", "Sydney"};
        int[] routeCodes = generateRandomArray(5, 10000, 19999);
        int[] sourceAndDestIndexPair;
        int trafficType, srcIndex, destIndex, flightFare, flightFrequency;
        String formattedRandomTime;

        for (int i = 0; i < 5; i++) {
            trafficType = generateRandomNumber(trafficTypes.length - 1);
            flightFare = generateRandomNumber(15000, 50000);
            flightFrequency = generateRandomNumber(1, 5);
            sourceAndDestIndexPair = generateRandomArray(2, 0, intlCities.length - 1);
            srcIndex = sourceAndDestIndexPair[0];
            destIndex = sourceAndDestIndexPair[1];
            formattedRandomTime = String.format("%02d:%02d:00", generateRandomNumber(24), generateRandomNumber(60));

            intlFlights[i] = new Route();
            intlFlights[i].setroute(trafficTypes[trafficType], routeCodes[i], intlCities[srcIndex], intlCities[destIndex], formattedRandomTime, flightFare, flightFrequency);
        }
    }
    public  void initializeDomesticFlight(Route[] intlFlights) {
        String[] trafficTypes = {"low", "busy", "moderate"};
        String[] intlCities = {"Kolkata", "Chennai",  "Delhi", "Mumbai"};
        int[] routeCodes = generateRandomArray(5, 10000, 19999);
        int[] sourceAndDestIndexPair;
        int trafficType, srcIndex, destIndex, flightFare, flightFrequency;
        String formattedRandomTime;

        for (int i = 0; i < 5; i++) {
            trafficType = generateRandomNumber(trafficTypes.length - 1);
            flightFare = generateRandomNumber(15000, 50000);
            flightFrequency = generateRandomNumber(1, 5);
            sourceAndDestIndexPair = generateRandomArray(2, 0, intlCities.length - 1);
            srcIndex = sourceAndDestIndexPair[0];
            destIndex = sourceAndDestIndexPair[1];
            formattedRandomTime = String.format("%02d:%02d:00", generateRandomNumber(24), generateRandomNumber(60));

            intlFlights[i] = new Route();
            intlFlights[i].setroute(trafficTypes[trafficType], routeCodes[i], intlCities[srcIndex], intlCities[destIndex], formattedRandomTime, flightFare, flightFrequency);
        }
    }

    public  int getNumberOfTicketsToBook(Scanner sc, String className, int maxSeats) {
        int ticketsToBeBooked = 0;
        do {
            System.out.print("\nHow many Tickets do you want to book in " + className + " class: ");
            ticketsToBeBooked = sc.nextInt();
            sc.nextLine();

            if (ticketsToBeBooked <= 0 || ticketsToBeBooked > maxSeats) {
                System.out.println("Enter a valid number of tickets between 1 and " + maxSeats + " in " + className + " class!");
            }
        } while (ticketsToBeBooked <= 0 || ticketsToBeBooked > maxSeats);

        return ticketsToBeBooked;
    }

	public  int[] seatAvailability(Scanner sc) {
        int[] seat = new int[3];
        int bookedEconomyclass = 0;
        int bookedFirstclass = 0;

        int totalSeats = (int) (Math.random() * 31 + 30);
        int EconomySeats = (int) (totalSeats * 0.7);
        int FirstclassSeats = totalSeats - EconomySeats;

        int freeEconomySeats = generateRandomNumber(1, EconomySeats);
        int freeFirstclassSeats = generateRandomNumber(1, FirstclassSeats);
        int totalFreeSeats = freeEconomySeats + freeFirstclassSeats;

        System.out.println("\n\nTotal Available Seats are : " + totalFreeSeats);
        System.out.println("Available  Economy Seats are : " + freeEconomySeats);
        System.out.println("Available First Class Seats are : " + freeFirstclassSeats);

        System.out.println("\nTo book an Economy class Ticket Enter 0 or");
        System.out.println("To book a First class Ticket Enter 1 : ");
        int choice = sc.nextInt();
        sc.nextLine();

        String className = (choice == 0) ? "Economy" : "First class";

        int maxSeats = (choice == 0) ? freeEconomySeats : freeFirstclassSeats;
        int ticketsToBeBooked = getNumberOfTicketsToBook(sc, className, maxSeats);

        if (choice == 0) {
            bookedEconomyclass = ticketsToBeBooked;
        } else {
            bookedFirstclass = ticketsToBeBooked;
        }

        seat[0] = Math.max(bookedEconomyclass, bookedFirstclass);
        seat[1] = choice;
        seat[2] = 1;

        System.out.println("Booked " + className + " Seats are : " + seat[0]);
        return seat;
    }
    public  boolean processCreditCardPayment(Scanner sc) {
        System.out.print("Enter credit card number: ");
        String creditCardNumber = sc.nextLine();
        if(creditCardNumber.length() != 16 ){
            System.out.println("Invalid credit card information. Payment failed.");
        }
        System.out.print("Enter expiration date (MM/YY): ");
        String expirationDate = sc.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = sc.nextLine();

        // Basic validation for demonstration purposes
        if (creditCardNumber.length() == 16 && expirationDate.matches("\\d{2}/\\d{2}") && cvv.length() == 3) {
            System.out.println("Payment successful! Thank you for using your credit card.");
            return true;
        } else {
            System.out.println("Invalid credit card information. Payment failed.");
            return false;
        }
    }


    public  String getTravelDate(Scanner sc, String presentDate, String nextDate, DateTimeFormatter df) {
        LocalDate userDate = null;
        LocalDate startDate = LocalDate.parse(presentDate, df);
        LocalDate endDate = LocalDate.parse(nextDate, df);

        do {
            System.out.print("Enter date of travel between " + presentDate + " to " + nextDate + " [DD-MM-YYYY] : ");
            String dateInput = sc.nextLine();

            try {
                userDate = LocalDate.parse(dateInput, df);
                if (userDate.isBefore(startDate) || userDate.isAfter(endDate)) {
                    System.out.println("Date must be between " + presentDate + " and " + nextDate + ".");
                    userDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter a date in the format DD-MM-YYYY.");
            }
        } while (userDate == null);
        return df.format(userDate);
    }
}

