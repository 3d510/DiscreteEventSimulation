import java.util.Random;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
    int numberOfMeasure = 0, numberOfArr = 0, accumulated = 0;
    int numberOfAvailServers;
	public SimulationData simDat;

	Random slump = new Random(); // This is just a random number generator
	
	public State(SimulationData simDat) {
		this.simDat = simDat;
		this.numberOfAvailServers = simDat.numberOfServers;
	}
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x) {
//        if (simDat.measureTime == 4 && simDat.numberOfMeasure == 1000)
//		    x.show();
		switch (x.eventType){
			case ARRIVE:
				arrive();
				break;
			case DEPART:
				depart();
				break;
			case MEASURE:
				measure();
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
        if(simDat.numberOfServers - numberOfAvailServers > 0)
            insertEvent(DEPART, time + simDat.serviceTime);
        insertEvent(ARRIVE, time + exp(1.0/simDat.arrivalRate));
	}
	
	private void depart() {
	    numberOfAvailServers++;
	}

	private void measure(){
//	    if (simDat.measureTime == 4 && simDat.numberOfMeasure == 1000)
//	        System.out.printf("%d %d %d\n", simDat.numberOfServers, numberOfAvailServers, simDat.numberOfServers - numberOfAvailServers);
	    accumulated += (simDat.numberOfServers - numberOfAvailServers);
        numberOfMeasure++;
		insertEvent(MEASURE, time + simDat.measureTime);
	}
	
	public double exp(double mean) {
		return -Math.log(1-slump.nextDouble())*mean;
	}
}