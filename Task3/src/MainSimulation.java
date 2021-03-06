import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
   
    	double[] arrTimes = {2,1.5,1.1}; 
    	
    	for (int i=0; i<3; i++) {
    		time = 0;
			eventList = new EventListClass();
        	Event actEvent;
        	State actState = new State(arrTimes[i]); // The state that should be used

        	// Some events must be put in the event list at the beginning
        	insertEvent(ARRIVEQ1, 0, 0);  
            insertEvent(MEASURE, 5, -1);
        	// The main simulation loop
        	while (actState.noMeasurements<1000) {
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        	}
        	System.out.printf("Interarrival time for Q1: %f\n", arrTimes[i]);
        	System.out.printf("Mean number of customers in both queues: %f\n", 1.0*actState.curCustomersSum/actState.noMeasurements);
        	System.out.printf("Mean time of customer in system: %f\n", actState.calTimeInSystem());
        	System.out.println();
    	}
    }
}