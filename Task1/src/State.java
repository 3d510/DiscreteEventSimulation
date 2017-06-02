import java.util.*;
import java.io.*;
import java.lang.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQ1 = 0, numberInQ2 = 0, curNoInQ2Sum = 0, noMeasurements = 0;
	public int noArrivals = 0, noRejected = 0;
	private double interArrTime;
	private double q1MeanServiceTime = 2.1, q2ServiceTime = 2, measureMeanTime = 5;

	Random slump = new Random(); // This is just a random number generator
	
	public State(double interArrTime) {
		this.interArrTime = interArrTime;
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
//		x.show();
		switch (x.eventType){
			case ARRIVEQ1:
				arrivalq1();
				break;
			case DEPARTQ1:
				departq1();
				break;
			case DEPARTQ2:
				departq2();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrivalq1(){
		noArrivals++;
		if (numberInQ1 == Q1CAPACITY) {
			noRejected++;
		} else {
            numberInQ1++;
		}
		if (numberInQ1 == 1)
			insertEvent(DEPARTQ1, time + exp(q1MeanServiceTime));
		insertEvent(ARRIVEQ1, time + interArrTime);
	}
	
	private void departq1(){
		numberInQ1--;
		if (numberInQ2 == 0) 
			insertEvent(DEPARTQ2, time + q2ServiceTime);
		numberInQ2++;
		if (numberInQ1 > 0)
			insertEvent(DEPARTQ1, time + exp(q1MeanServiceTime));
	}
	
	private void departq2() {
		numberInQ2--;
		if (numberInQ2 > 0)
			insertEvent(DEPARTQ2, time + q2ServiceTime);
	}
	
	private void measure(){
		curNoInQ2Sum = curNoInQ2Sum + numberInQ2;
		noMeasurements++;
		insertEvent(MEASURE, time + exp(measureMeanTime));
	}
	
	public double exp(double mean) {
		return -Math.log(1-slump.nextDouble())*mean;
	}
}