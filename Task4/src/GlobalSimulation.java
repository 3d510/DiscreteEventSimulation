public class GlobalSimulation{

	public static final int ARRIVE = 1, DEPART = 2, MEASURE = 3; // The events, add or remove if needed!
	public static double time = 0; // The global time variable
	public static EventListClass eventList; // The event list used in the program
	public static void insertEvent(int type, double TimeOfEvent){  // Just to be able to skip dot notation
		eventList.InsertEvent(type, TimeOfEvent);
	}
}