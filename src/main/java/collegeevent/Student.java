package collegeevent;
public class Student extends Person {
 private String collegeId,email,phone,department; private int semester;
 public Student(String collegeId,String name,String email,String phone,String department,int semester){
  super(name); this.collegeId=collegeId; this.email=email; this.phone=phone; this.department=department; this.semester=semester;
 }
 public String getCollegeId(){return collegeId;} public String getEmail(){return email;}
 public String getPhone(){return phone;} public String getDepartment(){return department;} public int getSemester(){return semester;}
 @Override public void displayRole(){System.out.println("Role: Student");}
}
