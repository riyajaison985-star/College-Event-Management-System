package collegeevent;
import java.sql.*;
public class OrganizerDAO {
 public Organizer login(String id,String password){
  String sql="SELECT * FROM organizers WHERE organizer_id=? AND password=?";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,id);p.setString(2,password);ResultSet r=p.executeQuery();
   if(r.next())return new Organizer(r.getString("organizer_id"),r.getString("name"));
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());}return null;
 }
 public void viewParticipants(String eventId){
  String sql="SELECT s.college_id,s.name,s.email,s.department,r.status FROM registrations r JOIN students s ON r.college_id=s.college_id WHERE r.event_id=?";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,eventId);ResultSet r=p.executeQuery();
   System.out.println("\n========== PARTICIPANTS ==========");
   while(r.next())System.out.println(r.getString("college_id")+" | "+r.getString("name")+" | "+r.getString("email")+" | "+r.getString("department")+" | "+r.getString("status"));
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());}
 }
}
