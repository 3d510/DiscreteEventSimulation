
// As the name indicates this class contains the definition of an event. next is needed to 
// build a linked list which is used by the EventListClass. It would have been just as easy
// to use a priority list which sorts events using eventTime.

class Event{
	public double eventTime;
	public int eventType;
	public Event next;
	public int jobType = 1; //initialize as job A, always
	
	public void show() {
		System.out.printf("%f %d\n", eventTime, eventType);
	}
}
