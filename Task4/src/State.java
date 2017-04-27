import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
    int numberOfMeasure = 0, accumulated = 0;
    int numberOfAvailServers;
	public SimulationData simDat;
	String fileName;
	FileWriter out;

	Random slump = new Random(); // This is just a random number generator
	
	public State(SimulationData simDat, String fileName) {
	    this.fileName = fileName;
        try {
            out = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.simDat = simDat;
		this.numberOfAvailServers = simDat.numberOfServers;
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
		switch (x.eventType){
			case ARRIVE:
				arrive();
				break;
			case DEPART:
				depart();
				break;
			case MEASURE:
                try {
                    measure();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
		}
	}
	
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrive(){
        if (numberOfAvailServers == 0)
            return;
        numberOfAvailServers--;
        if(simDat.numberOfServers - numberOfAvailServers > 0) // there are still customers in the system
            insertEvent(DEPART, time + simDat.serviceTime);
        insertEvent(ARRIVE, time + exp(1.0/simDat.arrivalRate));
	}
	
	private void depart() {
	    numberOfAvailServers++;
	}

	private void measure() throws IOException {
	    int numberOfCus = simDat.numberOfServers - numberOfAvailServers;
	    out.write("" +  time + " " + numberOfCus + "\r\n");
		accumulated += numberOfCus;
        numberOfMeasure++;
		insertEvent(MEASURE, time + simDat.measureTime);
	}
	
	public double exp(double mean) {
		return -Math.log(1-slump.nextDouble())*mean;
	}
}