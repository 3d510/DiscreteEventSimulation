import java.util.*;

public class EventListClass {
	
	private Event list, last; // Used to build a linked list
//	private List<Event> curList;
	
	EventListClass(){
		list = new Event();
    	last = new Event();
    	list.next = last;
//		curList = new ArrayList<Event>();
	}
	
	// The method insertEvent creates a new event, and searches the list of events for the 
	// right place to put the new event.
	
	public void InsertEvent(int type, double TimeOfEvent, int id){
	 	Event dummy, predummy;
	 	Event newEvent = new Event();
	 	newEvent.eventType = type;
	 	newEvent.eventTime = TimeOfEvent;
	 	newEvent.custId = id;
	 	predummy = list;
	 	dummy = list.next;
	 	while ((dummy.eventTime < newEvent.eventTime) & (dummy != last)){
	 		predummy = dummy;
	 		dummy = dummy.next;
	 	}
	 	predummy.next = newEvent;
	 	newEvent.next = dummy;
	
//		Event newEvent = new Event();
//		newEvent.eventType = type;
//		newEvent.eventTime = TimeOfEvent;
//		newEvent.custId = id;
//		int index = 0;
//		while (index < curList.size() && curList.get(index).eventTime < newEvent.eventTime) 
//			index++;
//		curList.add(index,newEvent);
	}
	
	
	
	// The following method removes and returns the first event in the list. That is the
	// event with the smallest time stamp, i.e. the next thing that shall take place.
	
	public Event fetchEvent(){
		Event dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
//		Event ret = curList.get(0);
//		curList.remove(0);
//		return ret;
	}
	
//	public Event findEvent(int type) {
//		Event ret = new Event();
//		for (Event e: curList) {
//			if (e.eventType == type && !exist(type + 1, e.custId))
//				return e;
//		}
//		return ret;
//	}
//	
//	private boolean exist(int type, int custId) {
//		for (Event e: curList) 
//			if (e.eventType == type && e.custId == custId)
//				return true;
//		return false;
//	}
}