import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
   
    	int numberOfDays = 1000; 
    	double curFinishTimeSum = 0;
    	
    	for (int i=0; i<numberOfDays; i++) {
    		time = startTime;
			eventList = new EventListClass();
        	Event actEvent;
        	State actState = new State(); // The state that should be used

        	// Some events must be put in the event list at the beginning
        	insertEvent(ARRIVE, startTime, 0);  
        	// The main simulation loop
        	while (!actState.isFinished()) {
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        	}
        	curFinishTimeSum += actState.finishTime;
        	System.out.println("Working day " + (i+1) + ":");
        	System.out.printf("Finish Time: %f\n", actState.finishTime/60);
        	System.out.printf("Average time of the form in the system: %f mins\n", actState.curTimeInSystemSum/actState.noArrivals); 
        	System.out.println();
    	}
    	System.out.printf("Mean finish time is %s\n", displayTime((curFinishTimeSum/numberOfDays)/60));
    }
    
    public static String displayTime(double timeInHours) {
    	int hour = (int)timeInHours;
    	int min = (int)(60.0 * (timeInHours - (double)hour));
    	return "" + hour + ":" + min;
    }
}