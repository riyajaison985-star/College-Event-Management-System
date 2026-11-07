package collegeevent;
import java.time.LocalDateTime;
public class Registration {
 private int registrationId; private String collegeId,eventId,status; private LocalDateTime registrationDate;
 public Registration(int registrationId,String collegeId,String eventId,LocalDateTime registrationDate,String status){
  this.registrationId=registrationId;this.collegeId=collegeId;this.eventId=eventId;this.registrationDate=registrationDate;this.status=status;
 }
 public int getRegistrationId(){return registrationId;} public String getCollegeId(){return collegeId;} public String getEventId(){return eventId;}
 public LocalDateTime getRegistrationDate(){return registrationDate;} public String getStatus(){return status;}
}
