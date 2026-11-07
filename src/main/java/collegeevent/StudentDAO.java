package collegeevent;
import java.sql.*;
public class StudentDAO {
 public boolean verifyCollegeStudent(String id){
  String sql="SELECT college_id FROM college_students WHERE college_id=?";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,id);return p.executeQuery().next();
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());return false;}
 }
 public boolean registerStudent(Student s,String password){
  String sql="INSERT INTO students(college_id,name,email,phone,department,semester,password) VALUES(?,?,?,?,?,?,?)";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,s.getCollegeId());p.setString(2,s.getName());p.setString(3,s.getEmail());p.setString(4,s.getPhone());
   p.setString(5,s.getDepartment());p.setInt(6,s.getSemester());p.setString(7,password);p.executeUpdate();return true;
  }catch(SQLException e){System.out.println("Could not create account: "+e.getMessage());return false;}
 }
 public Student login(String id,String password){
  String sql="SELECT * FROM students WHERE college_id=? AND password=?";
  try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,id);p.setString(2,password);ResultSet r=p.executeQuery();
   if(r.next())return new Student(r.getString("college_id"),r.getString("name"),r.getString("email"),r.getString("phone"),r.getString("department"),r.getInt("semester"));
  }catch(SQLException e){System.out.println("Database error: "+e.getMessage());}
  return null;
 }
}
