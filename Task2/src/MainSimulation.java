import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
   
    	double[] arrTimes = {150,2,5}; 
    	
    	for (int i=0; i<3; i++) {
    		time = 0;
        	Event actEvent;
        	State actState = new State(arrTimes[i]); // The state that shoud be used

        	// Some events must be put in the event list at the beginning
        	insertEvent(ARRIVALJA, 0);  
            insertEvent(MEASURE, 0.1);
        	// The main simulation loop
        	while (actState.noMeasurements<1000){
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        		System.out.println(time);
        	}
        	System.out.printf("Interarrival time for Q1: %f\n", arrTimes[i]);
        	System.out.printf("Mean number of customers in Q2: %f\n", 1.0*actState.accumulated/actState.noMeasurements);
        	System.out.printf("Probability of rejected customers: %f\n", actState.noRejected*1.0/actState.noArrivals);
        	System.out.println();
    	}
    	
    }
}