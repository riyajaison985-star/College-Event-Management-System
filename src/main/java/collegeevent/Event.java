package collegeevent;
import java.time.LocalDate; import java.time.LocalTime;
public class Event {
 private String eventId,eventName,venue,description; private LocalDate date; private LocalTime time; private int capacity;
 public Event(String eventId,String eventName,LocalDate date,LocalTime time,String venue,int capacity,String description){
  this.eventId=eventId;this.eventName=eventName;this.date=date;this.time=time;this.venue=venue;this.capacity=capacity;this.description=description;
 }
 public String getEventId(){return eventId;} public String getEventName(){return eventName;} public LocalDate getDate(){return date;}
 public LocalTime getTime(){return time;} public String getVenue(){return venue;} public int getCapacity(){return capacity;} public String getDescription(){return description;}
}
