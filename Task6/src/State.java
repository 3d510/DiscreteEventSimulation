import java.util.*;
import java.io.*;
import java.lang.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQ = 0;
	public int noArrivals = 0, noRejected = 0;
	public double curTimeInSystemSum = 0, finishTime;
	private double minServiceTime = 10, maxServiceTime = 20, meanArrivalTime = 15; // minutes
	private int nextCust = 0;
	public List<Double> customerEnterTime = new ArrayList<Double>();

	Random slump = new Random(); // This is just a random number generator
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
//		x.show();
		switch (x.eventType){
			case ARRIVE:
				arrival(x);
				break;
			case DEPART:
				departure(x);
				break;
		}
	}
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrival(Event x){
		if (x.eventTime <= endTime) {
			noArrivals++;
			numberInQ++;
			customerEnterTime.add(x.eventTime);
			if (numberInQ == 1) 
				insertEvent(DEPART, time + uniform(minServiceTime, maxServiceTime), nextCust);
			insertEvent(ARRIVE, time + exp(meanArrivalTime), noArrivals);
		}
	}
	
	private void departure(Event x) {
		nextCust++;
		numberInQ--;
		if (x.custId < customerEnterTime.size())
			curTimeInSystemSum += (x.eventTime - customerEnterTime.get(x.custId));
		if (numberInQ > 0)
			insertEvent(DEPART, time + uniform(minServiceTime, maxServiceTime), nextCust);
	}
	
	public boolean isFinished() {
		if (time > endTime && numberInQ == 0) {
			finishTime = time;
			return true;
		}
		return false;
	}
	
	private double exp(double mean) {
		return -Math.log(1-slump.nextDouble())*mean;
	}
	
	private double uniform(double min, double max) {
		return min + (max - min) * slump.nextDouble();
	}
}