import java.util.*;
import java.io.*;
import java.lang.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQ1 = 0, numberInQ2 = 0, accumulated = 0, noMeasurements = 0;
	public int noArrivals = 0, noRejected = 0;
	private double interArrTime;

	Random slump = new Random(); // This is just a random number generator
	
	public State(double interArrTime) {
		this.interArrTime = exp(interArrTime);
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
//		x.show();
		switch (x.eventType){
			case ARRIVALJA:
				arrivalJobA();
				break;
			case ARRIVALJB:
				arrivalJobB();
				break;				
			case DEPARTJA:
				departJobA();
				break;
			case DEPARTJB:
				departJobB();
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
	
	private void arrivalJobA(){
		if (numberInQ1 == 0 && numberInQ2 == 0){
				insertEvent(DEPARTJA, time + 0.002);
		}
		numberInQ1++;
		insertEvent(ARRIVALJA, time + interArrTime);
	}
	
	private void arrivalJobB(){
		if (numberInQ2 == 0){
			insertEvent(DEPARTJB, time + 0.004);
		}
	}
	
	private void departJobA(){
		numberInQ1--;
		if(numberInQ2 == 0)
			insertEvent(DELAY, time + 1);
		if (numberInQ1 > 0)
			insertEvent(DEPARTJA, time + 0.002);	
	}
	
	private void departJobB(){
		numberInQ2--;
		if (numberInQ2 > 0)
			insertEvent(DEPARTJB, time + 0.004);
	}
	
	private void delay() {
		insertEvent(ARRIVALJB, time + 0.004);
	}
	
	private void measure(){
		accumulated = accumulated + numberInQ1;
		noMeasurements++;
		insertEvent(MEASURE, time + 0.1);
	}
	
	public double exp(double mean) {
		return -Math.log(1-slump.nextDouble())*mean;
	}
	
}