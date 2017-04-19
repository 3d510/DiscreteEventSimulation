import java.util.*;
import java.io.*;
import java.lang.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	
	boolean[] compWorking = new boolean[6];
	public boolean isFinished;
	Random slump = new Random(); // This is just a random number generator
	
	public State() {
		for (int i=1; i<=5; i++)
			compWorking[i] = true;
		isFinished = false;
	}
	
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
	
	private void arrival(Event x) {
		insertEvent(DEPART, time + uniform(1,5), x.component);
	}
	
	private void departure(Event x) {
		int comp = x.component;
		compWorking[comp] = false;
		if (comp == 1) {
			compWorking[2] = false;
			compWorking[5] = false;
		}
		if (comp == 3) 
			compWorking[4] = false;
	}
	
	public void updateStatus() {
		isFinished = true;
		for (int i=1; i<=5; i++) {
			if (compWorking[i])
				isFinished = false;
		}
	}
	
	private double uniform(double min, double max) {
		return min + (max - min) * slump.nextDouble();
	}
}