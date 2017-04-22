import java.util.*;
import java.io.*;
import java.lang.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQ = 0, accumulated = 0, noMeasurements = 0;
	public int noArrivals = 0, noRejected = 0;
	private double interArrTime;

	Random slump = new Random(); // This is just a random number generator
	
	public State(double interArrTime) {
		this.interArrTime = interArrTime;
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
//		x.show();
		switch (x.eventType){
			case ARRIVAL:
				arrival(x.jobType);
				break;
			case DEPART:
				depart(x.jobType);
				break;
			case DELAY: //sending job A to be B
				delay();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrival(int jobType){
		if (numberInQ == 0){
			if(jobType == 1) //job A
				insertEvent(DEPART, time + 0.002);
			else //job B
				insertEvent(DEPART, time + 0.004);
		}
		numberInQ++;
		insertEvent(ARRIVAL, time + interArrTime);
	}
	
	private void depart(int jobType){
		numberInQ--;
		if (numberInQ > 0)
			if(jobType == 1){ // job A
				insertEvent(DELAY, time + 1);
			}
			insertEvent(DEPART, time + 0.002); //NOT SURE
	}
	
	private void delay() {
		insertEvent(ARRIVAL, time + 0.004); //NOT SURE
	}
	
	private void measure(){
		accumulated = accumulated + numberInQ;
		noMeasurements++;
		insertEvent(MEASURE, time + 0.1);
	}
	
}