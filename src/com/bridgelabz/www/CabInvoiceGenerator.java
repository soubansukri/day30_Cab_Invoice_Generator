package com.bridgelabz.www;

public class CabInvoiceGenerator {

		private final int costPerKm = 10;
		private final int costPerMinute = 1;
		public int minfare = 5;
		double totalFare;
		
		RideRepository rideRepository = new RideRepository();

		public double calculateFare(double DISTANCE, int TIME) {

			totalFare = costPerKm * DISTANCE + costPerMinute * TIME;
			if (totalFare < minfare)
				totalFare = minfare;
			return totalFare;
		}

		public double calculateFare(Rides[] rides) {
			double totalFare = 0.0;
			for (Rides ride : rides)
				totalFare = totalFare + this.calculateFare(ride.distance, ride.time);
			return totalFare;
		}

		public int numberOfRides(Rides[] rides) {
			return rides.length;
		}

		public double calculateAverageFarePerRide(Rides[] rides) {
			CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
			double totalFare = cabInvoiceGenerator.calculateFare(rides);
			double numberOfRides = rides.length;
			double averageFare = totalFare / numberOfRides;
			return averageFare;
		}

		public double getRidesDetails(String userID) {
			return this.calculateFare(rideRepository.getRides(userID));
		}

		public void addRides(String userID, Rides[] rides) {
			rideRepository.addRides(userID, rides);
		}
	}
}
