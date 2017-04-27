import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	
    	double curTimeSum = 0;
    	int numDays = 1000;
    	
    	for (int day = 1; day <= numDays; day++) {
    		time = 0;
			eventList = new EventListClass();
        	Event actEvent;
        	State actState = new State();
    		for (int i = 1; i < 5; i++) {
    			insertEvent(ARRIVE, 0, i);
    		}
    		while (true) {
    			actEvent = eventList.fetchEvent();
    			time = actEvent.eventTime;
    			actState.treatEvent(actEvent);
    			actState.updateStatus();
    			if (actState.isFinished) 
    				break;
    		}
    		curTimeSum += time;
    		System.out.printf("The time until system breaks down on day %d is: %f\n", day, time);
    	}
    	
    	System.out.println("\nMean time to break down of system is " + curTimeSum/numDays);
    	
    }
    
}