public class Aircraft {
	String aircraftModel;
	String aircraftType;
	int pcapacity;
	int fuelcapacity;

	public void setdetails(String model, String type, int pct, int fct) {
		this.aircraftModel = model;
		this.aircraftType = type;
		this.pcapacity = pct;
		this.fuelcapacity = fct;
	}

	public void getdetails() {
		System.out.println("Aircraft Model: " + aircraftModel);
		System.out.println("Aircraft Type: " + aircraftType);
		System.out.println("Passenger Capacity: " + pcapacity);
		System.out.println("Fuel Capacity: " + fuelcapacity + " Litres\n");
	}
}

