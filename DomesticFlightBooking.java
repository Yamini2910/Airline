import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DomesticFlightBooking {

    private static int id = 0;
    private static int flag = 0;
   

    public static void bookDomesticFlight(Scanner sc, Aircraft[] acft, Route[] flt, int totalRoutes, Ticket[] tck, Route[] intlFlights) {
       LocalDate dt = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String presentDate = dt.format(df);

        LocalDate dtNext = dt.plusMonths(3);
        String nextDate = dtNext.format(df);

        System.out.println("\n--- Booking Domestic Flight ---");
        int op = 1, cnf, pmt, id = 0, flag = 0;

        String section;
        String[] city = {"Delhi", "Mumbai", "Chennai", "Kolkata"};
        String[] airportType = {"Single", "Multihop"};
        int[] arr = new int[5];
        int[] mark = new int[5];
        Scanner scanner = new Scanner(System.in);
        AirlineTicketSystem airlineTicketSystem = new AirlineTicketSystem();

        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("--------WELCOME TO AIRINDIA TICKET RESERVATION CENTER--------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("For Flight Booking of : Delhi-Mumbai-Chennai-Kolkata");
        System.out.println("Book your advance tickets between " + presentDate + " to " + nextDate + " now.\n");

         acft = new Aircraft[5];
        airlineTicketSystem.initializeAircrafts(acft);

        totalRoutes = 20;
         flt = new Route[totalRoutes];
        airlineTicketSystem.initializeFlightRoutes(flt, totalRoutes, city);

        tck = new Ticket[10];
        airlineTicketSystem.initializeTickets(tck);
        

        do {
            System.out.println("1.Flight Booking\t\t2.Cancellation of Ticket\t\t3.To check details of Reserved Ticket");
            System.out.print("Enter your choice : ");
            int ch = sc.nextInt();
            sc.nextLine();
            int tkt = 0;
            

            switch (ch) {
                case 1:
                    
    System.out.println("\n0.Delhi\t\t1.Mumbai\t\t2.Chennai\t\t3.Kolkata");
    System.out.print("Select your Departure city : ");
    int depr = sc.nextInt();
    sc.nextLine();
    System.out.print("Select your Arrival city : ");
    int arvl = sc.nextInt();
    sc.nextLine();

    if (depr >= 0 && depr < 4 && arvl >= 0 && arvl < 4 && depr != arvl) {
        String date = airlineTicketSystem.getTravelDate(sc, presentDate, nextDate, df);
        

        System.out.printf("\n-----------------Available Flights :------------------\n\n");
        for (int i = 0; i < 20; i++) {
            if ((flt[i].src.compareTo(city[depr]) == 0) && (flt[i].dest.compareTo(city[arvl]) == 0)) {
                System.out.println("Press  " + i);
                flt[i].getroute();
                acft[i % 5].getdetails();
                System.out.println("\n\n");
                mark[i % 2] = i;
            }
        }
        System.out.println("Please be careful while Entering Flight choice : ");
        tkt = sc.nextInt();
             sc.nextLine();
             if (tkt >= 20 && tkt < 25) {
                        System.out.println("\nInternational Flights :");
                        intlFlights[tkt - 20].getroute();
                    }
        

        if (tkt != mark[0] && tkt != mark[1]) {
            System.out.println("Enter a valid choice!!");
            break;
        } else {
            System.out.println("\nSelected Flight :");
            flt[tkt].getroute();
            arr = airlineTicketSystem.seatAvailability(sc);
            if (arr[2] == 1) {
                
                if (arr[1] == 0) {
                    section = "Economy Class";
                } else if (arr[1] == 1) {
                    section = "First Class";
                } else {
                    System.out.println("Invalid section! Please enter a valid choice.");
                    break;
                }

                System.out.println("\nPress 1 to confirm selected flight else press 0 :");
                cnf = sc.nextInt();
                sc.nextLine();
if (cnf == 1) {
                                System.out.println("press 1 to make payment and book ticket else press 0 : ");
                                pmt = sc.nextInt();
                                sc.nextLine();
                                if (pmt == 1) {
                            boolean paymentSuccess = airlineTicketSystem.processCreditCardPayment(sc);

                            if (paymentSuccess) {
                                System.out.println("\nTicket booked successfully!!\n");
                                System.out.println("\n----------------Your Ticket Details--------------");
                                tck[id].setticket(id, date, section, arr[0], tkt);
                                if (arr[1] == 1)
                                    flt[id].fare *= 2;
                                tck[id].getticket();
                                flt[tkt].getroute();
                                id++;
                            } else {
                                // Payment failed, handle accordingly
                                System.out.println("Payment failed. Please try again.");
                                break;
                            }
                        }
                    }
                } else if (depr == arvl) {
                    System.out.println("\n\nError! You have entered the same Departure and Arrival city!!");
                } else {
                    System.out.println("\nError! Please enter a Valid City Choice!!");
                } break;
                    }
                }


                case 2:
                
                    System.out.print("Enter your Ticket Id : ");
                    int del = sc.nextInt();
                    sc.nextLine();
                    flag = 0;
                    if (tkt >= 20 && tkt < 25) {
                        System.out.println("\nInternational Flights :");
                        intlFlights[tck[del].code - 20].getroute();
                    }
                    for (int i = 0; i < 10; i++) {
                        if (del == tck[i].getTicketId()) {
                            System.out.println("\n----------------Your Ticket Details--------------");
                            tck[del].getticket();
                            flt[tck[del].code].getroute();
                            System.out.println("Press 1 to confirm Cancellation of Your Ticket else press 0 : ");
                            int d = sc.nextInt();
                            sc.nextLine();
                            flag = 1;
                            if (d == 1) {
                                tck[del].ticketId = 111;
                                System.out.println("\nYour Ticket has been cancelled Successfully.");
                                System.out.println("You will get Refund Amount within two days.\n\n");
                            }
                            break;
                        }
                    }
                    if (flag == 0)
                        System.out.println("No such ticket exists with the entered Ticket Id!");
                    break;

                case 3:
                    System.out.print("Enter your Ticket Id : ");
                    int find = sc.nextInt();
                    sc.nextLine();
                    int temp1 = airlineTicketSystem.generateRandomNumber(airportType.length-1);
                    int temp2 = airlineTicketSystem.generateRandomNumber(airportType.length-1);

                    flag = 0;
                    for (int i = 0; i < 10; i++) {
                        if (find == tck[i].ticketId) {
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.print("Your flight will take off from " + flt[tck[find].code].src);
                            System.out.println(" airport which is a " + airportType[temp1] + " runway airport.");
                            System.out.print("Your flight will land on " + flt[tck[find].code].dest);
                            System.out.println(" airport which is a " + airportType[temp2] + " runway airport.");
                            System.out.println("\n----------------Your Ticket Details--------------");
                            tck[find].getticket();
                            System.out.println();
                            flt[tck[find].code].getroute();
                            acft[tck[find].code % 5].getdetails();
                            flag = 1;
                            break;
                        }
                    }
                    for (int i = 0; i < tck.length; i++) {
        if (tck[i].hasSameTicketId(find)) {
            tck[i].getticket();  // Retrieve ticket details
            // Additional actions if needed
            flag = 1;
            break;
        }
    }

                    if (flag == 0) {
                        System.out.println("No such ticket exists with the entered Ticket Id!");
                    }
                    break;

                default:
                    System.out.println("Error! Please enter a valid choice!");
            }

            System.out.println("\nDo you want to continue? press 1 if 'yes' and press 0 if 'no': ");
            op = sc.nextInt();
            sc.nextLine();
        } while (op != 0);

        airlineTicketSystem.serializeTickets(tck);
        airlineTicketSystem.initializeDomesticFlight(intlFlights);

        System.out.println("\n\n--------------------------------------------------------------------");
        System.out.println("------------------------THANK YOU FOR VISITING----------------------");
        System.out.println("---------------AIRINDIA TICKET RESERVATION CENTER-----------");
        System.out.println("--------------------------------------------------------------------");

       
    
    
        System.out.println("\nBooking available for dates between " + presentDate + " to " + nextDate);
    }
}

