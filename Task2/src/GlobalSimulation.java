public class GlobalSimulation{
	
	// This class contains the definition of the events that shall take place in the
	// simulation. It also contains the global time, the event list and also a method
	// for insertion of events in the event list. That is just for making the code in
	// MainSimulation.java and State.java simpler (no dot notation is needed).
	
	public static final int ARRIVALJA = 1, ARRIVALJB = 2, DEPARTJA = 3, DEPARTJB = 4, DELAY = 5, MEASURE = 6; // The events, add or remove if needed!
	public static double time = 0; // The global time variable
	public static EventListClass eventList = new EventListClass(); // The event list used in the program
	public static void insertEvent(int type, double TimeOfEvent){  // Just to be able to skip dot notation
		eventList.InsertEvent(type, TimeOfEvent);
	}
}