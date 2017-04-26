import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
   
		String[] messages = {"Question a: Job B has higher priority and delay is constant",
                             "Question b: Job B has higher priority and delay has exp distribution",
                             "Question c: Job A has higher priority and delay is constant"
                            };
        StateBase[] states = new StateBase[3];
		states[0] = new State0();
        states[1] = new State1();
        states[2] = new State2();

    	for (int i=0; i<3; i++) {
    		time = 0;
        	Event actEvent;
        	StateBase actState = states[i]; // The state that shoud be used

        	// Some events must be put in the event list at the beginning
        	insertEvent(ARRIVEQA, 0);
            insertEvent(MEASURE, 0.1);
        	// The main simulation loop
        	while (actState.noMeasurements<1000){
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        	}
            System.out.println(messages[i]);
        	System.out.printf("Mean number of customers in queue: %f\n", 1.0*actState.accumulated/actState.noMeasurements);
        	System.out.println();
    	}
    }


}