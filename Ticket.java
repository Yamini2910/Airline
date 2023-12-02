import java.io.Serializable;
class Ticket implements Serializable  {
    private static final long serialVersionUID = 1L;
    private static int lastAssignedTicketId = 0;

    private boolean isReserved = false;
    private boolean isPaid = false;
    private int[] seatAvailability = {100, 50}; 
    int  code;
	String flightDate;
	String seatType;
	int noSeats;
	
     int ticketId;
     public boolean hasSameTicketId(int ticketId) {
    return this.ticketId == ticketId;
}

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }



	public void setticket(int ticketId, String dot, String s, int nos, int cd) {
		this.ticketId = ticketId;
		this.flightDate = dot;
		this.seatType = s;
		this.noSeats = nos;
		this.code = cd;
	}

	public void getticket() {
		System.out.println("\nTicket Id: " + ticketId);
		System.out.println("Flight Date: " + flightDate);
		System.out.println("Seat type: " + seatType);
		System.out.println("No of seats booked: " + noSeats);
	}
	public int getTicketId() {
        return ticketId;
    }

    
}

