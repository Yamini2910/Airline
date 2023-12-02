public class Route {
	String trafficStatus;
	int routeCode;
	String src;
	String dest;
	String departureTime;
	int fare;
	int frequency;

	public void setroute(String st, int rcode, String src, String dst, String time, int fare, int fq) {
		this.trafficStatus = st;
		this.routeCode = rcode;
		this.src = src;
		this.dest = dst;
		this.departureTime = time;
		this.fare = fare;
		this.frequency = fq;
	}

	public void getroute() {
		System.out.println("Flight No: " + routeCode);
		System.out.println("Departure: " + src);
		System.out.println("Arrival: " + dest);
		System.out.println("Departure Time: " + departureTime);
		System.out.println("Fare per seat: " + fare);
		System.out.println("Traffic in this route is: " + trafficStatus);
		System.out.println("This route is operated " + frequency + " times a week.\n");
	}
}

