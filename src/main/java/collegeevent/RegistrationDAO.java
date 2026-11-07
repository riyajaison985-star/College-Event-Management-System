package collegeevent;
import java.sql.*;
public class RegistrationDAO {
 public boolean register(String collegeId,String eventId){
  try(Connection c=DatabaseConnection.getConnection()){
   c.setAutoCommit(false);
   try(PreparedStatement ep=c.prepareStatement("SELECT capacity FROM events WHERE event_id=?")){
    ep.setString(1,eventId);ResultSet er=ep.executeQuery();
    if(!er.next()){System.out.println("Event does not exist.");c.rollback();return false;}
    int capacity=er.getInt("capacity");
    try(PreparedStatement cp=c.prepareStatement("SELECT COUNT(*) FROM registrations WHERE event_id=? AND status='REGISTERED'")){
     cp.setString(1,eventId);ResultSet cr=cp.executeQuery();cr.next();
     if(cr.getInt(1)>=capacity){System.out.println("Sorry, no seats are available.");c.rollback();return false;}
    }
    try(PreparedStatement ip=c.prepareStatement("INSERT INTO registrations(college_id,event_id,status) VALUES(?,?,'REGISTERED')")){
     ip.setString(1,collegeId);ip.setString(2,eventId);ip.executeUpdate();
    }
    c.commit();System.out.println("Registration successful!");return true;
   }catch(SQLException e){c.rollback();if(e.getErrorCode()==1062)System.out.println("You are already registered for this event.");else System.out.println("Registration failed: "+e.getMessage());return false;}
   finally{c.setAutoCommit(true);}
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());return false;}
 }
 public void viewMyRegistrations(String id){
  String sql="SELECT r.registration_id,e.event_id,e.event_name,e.event_date,e.venue,r.status FROM registrations r JOIN events e ON r.event_id=e.event_id WHERE r.college_id=?";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,id);ResultSet r=p.executeQuery();
   System.out.println("\n========== MY REGISTRATIONS ==========");
   while(r.next()){System.out.println("Registration ID: "+r.getInt("registration_id"));System.out.println("Event: "+r.getString("event_name"));
    System.out.println("Date: "+r.getDate("event_date"));System.out.println("Venue: "+r.getString("venue"));System.out.println("Status: "+r.getString("status"));System.out.println("--------------------------------------");}
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());}
 }
 public boolean cancel(int registrationId,String collegeId){
  String sql="UPDATE registrations SET status='CANCELLED' WHERE registration_id=? AND college_id=? AND status='REGISTERED'";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,registrationId);p.setString(2,collegeId);return p.executeUpdate()>0;}
  catch(SQLException e){System.out.println("Database error: "+e.getMessage());return false;}
 }
}
