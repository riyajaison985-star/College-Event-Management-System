package collegeevent;
import java.sql.*;
public class EventDAO {
 public void viewEvents(){
  String sql="SELECT e.*,(e.capacity-(SELECT COUNT(*) FROM registrations r WHERE r.event_id=e.event_id AND r.status='REGISTERED')) AS available FROM events e ORDER BY e.event_date,e.event_time";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){
   System.out.println("\n========== AVAILABLE EVENTS ==========");
   while(r.next()){System.out.println("ID: "+r.getString("event_id"));System.out.println("Name: "+r.getString("event_name"));
    System.out.println("Date: "+r.getDate("event_date"));System.out.println("Time: "+r.getTime("event_time"));System.out.println("Venue: "+r.getString("venue"));
    System.out.println("Available seats: "+r.getInt("available"));System.out.println("Description: "+r.getString("description"));System.out.println("--------------------------------------");}
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());}
 }
 public boolean addEvent(Event e,String organizerId){
  String sql="INSERT INTO events(event_id,event_name,event_date,event_time,venue,capacity,description,organizer_id) VALUES(?,?,?,?,?,?,?,?)";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,e.getEventId());p.setString(2,e.getEventName());p.setDate(3,Date.valueOf(e.getDate()));p.setTime(4,Time.valueOf(e.getTime()));
   p.setString(5,e.getVenue());p.setInt(6,e.getCapacity());p.setString(7,e.getDescription());p.setString(8,organizerId);p.executeUpdate();return true;
  }catch(SQLException ex){System.out.println("Could not create event: "+ex.getMessage());return false;}
 }
}
