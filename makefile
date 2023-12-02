all:Main.java DomesticFlightBooking.java InternationalFlightBooking.java Route.java Aircraft.java Ticket.java Reserve.java AirlineTicketSystem.java
	javac Main.java DomesticFlightBooking.java InternationalFlightBooking.java Route.java Aircraft.java Ticket.java Reserve.java AirlineTicketSystem.java

run: all
	java Main

jar: all
	jar cfe Airline.jar Main Main.class DomesticFlightBooking.class InternationalFlightBooking.class Route.class Aircraft.class Ticket.class Reserve.class AirlineTicketSystem.class Reserve.class
clean:
	rm *.class *.jar


