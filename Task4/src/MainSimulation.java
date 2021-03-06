import java.io.File;
import java.io.IOException;

public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
   
    	SimulationData[] data = new SimulationData[6];
    	data[0] = new SimulationData(1000, 100,8, 1,1000);
        data[1] = new SimulationData(1000, 10,80, 1,1000);
        data[2] = new SimulationData(1000, 200,4, 1,1000);
        data[3] = new SimulationData(100, 10,4, 4,1000);
        data[4] = new SimulationData(100, 10,4, 1,4000);
        data[5] = new SimulationData(100, 10,4, 4,4000);
        String prefixFilePath = System.getProperty("user.dir") + File.separator + "Task4" + File.separator;

    	for (int i=0; i<6; i++) {
    		time = 0;
			eventList = new EventListClass();
        	Event actEvent;
        	State actState = new State(data[i], prefixFilePath + "Question" + (i+1) + ".txt");
        	// Some events must be put in the event list at the beginning
        	insertEvent(ARRIVE, 0);
            insertEvent(MEASURE, actState.simDat.measureTime);
        	// The main simulation loop
        	while (actState.numberOfMeasure < actState.simDat.numberOfMeasure) {
        		actEvent = eventList.fetchEvent();
        		time = actEvent.eventTime;
        		actState.treatEvent(actEvent);
        	}
        	actState.out.close();
        	System.out.println("Mean no of customers: " + actState.accumulated*1.0/actState.numberOfMeasure);
    	}
    }
}