package collegeevent;
import java.time.*;import java.util.Scanner;
public class Main {
 private static final Scanner sc=new Scanner(System.in);private static final StudentDAO studentDAO=new StudentDAO();private static final EventDAO eventDAO=new EventDAO();
 private static final RegistrationDAO registrationDAO=new RegistrationDAO();private static final OrganizerDAO organizerDAO=new OrganizerDAO();
 public static void main(String[] args){
  System.out.println("==========================================");System.out.println("   COLLEGE EVENT MANAGEMENT SYSTEM");System.out.println("==========================================");
  while(true){System.out.println("\n1. Student Registration\n2. Student Login\n3. Organizer Login\n4. View Events\n5. Exit");System.out.print("Enter choice: ");
   switch(sc.nextLine()){case "1"->studentRegistration();case "2"->studentLogin();case "3"->organizerLogin();case "4"->eventDAO.viewEvents();case "5"->{System.out.println("Thank you!");return;}default->System.out.println("Invalid choice.");}}
 }
 private static void studentRegistration(){
  System.out.println("\n--- STUDENT REGISTRATION ---");System.out.print("College ID: ");String id=sc.nextLine();
  if(!studentDAO.verifyCollegeStudent(id)){System.out.println("Access rejected: College ID could not be verified.");return;}
  System.out.print("Name: ");String name=sc.nextLine();System.out.print("Email: ");String email=sc.nextLine();System.out.print("Phone: ");String phone=sc.nextLine();
  System.out.print("Department: ");String dept=sc.nextLine();System.out.print("Semester: ");int sem=Integer.parseInt(sc.nextLine());System.out.print("Password: ");String pw=sc.nextLine();
  if(studentDAO.registerStudent(new Student(id,name,email,phone,dept,sem),pw))System.out.println("Account created successfully!");
 }
 private static void studentLogin(){
  System.out.println("\n--- STUDENT LOGIN ---");System.out.print("College ID: ");String id=sc.nextLine();System.out.print("Password: ");String pw=sc.nextLine();
  Student s=studentDAO.login(id,pw);if(s==null){System.out.println("Invalid College ID or password.");return;}System.out.println("Welcome, "+s.getName()+"!");s.displayRole();
  while(true){System.out.println("\n1. View Events\n2. Register for Event\n3. View My Registrations\n4. Cancel Registration\n5. Logout");System.out.print("Enter choice: ");
   switch(sc.nextLine()){case "1"->eventDAO.viewEvents();case "2"->{eventDAO.viewEvents();System.out.print("Enter Event ID: ");registrationDAO.register(s.getCollegeId(),sc.nextLine());}
   case "3"->registrationDAO.viewMyRegistrations(s.getCollegeId());case "4"->{registrationDAO.viewMyRegistrations(s.getCollegeId());System.out.print("Enter Registration ID to cancel: ");int r=Integer.parseInt(sc.nextLine());System.out.println(registrationDAO.cancel(r,s.getCollegeId())?"Registration cancelled.":"Could not cancel registration.");}
   case "5"-> {return;}default->System.out.println("Invalid choice.");}}
 }
 private static void organizerLogin(){
  System.out.println("\n--- ORGANIZER LOGIN ---");System.out.print("Organizer ID: ");String id=sc.nextLine();System.out.print("Password: ");String pw=sc.nextLine();Organizer o=organizerDAO.login(id,pw);
  if(o==null){System.out.println("Invalid organizer credentials.");return;}System.out.println("Welcome, "+o.getName()+"!");
  while(true){System.out.println("\n1. Create Event\n2. View Events\n3. View Participants\n4. Logout");System.out.print("Enter choice: ");
   switch(sc.nextLine()){case "1"->createEvent(o);case "2"->eventDAO.viewEvents();case "3"->{System.out.print("Enter Event ID: ");organizerDAO.viewParticipants(sc.nextLine());}case "4"->{return;}default->System.out.println("Invalid choice.");}}
 }
 private static void createEvent(Organizer o){
  System.out.println("\n--- CREATE EVENT ---");System.out.print("Event ID: ");String id=sc.nextLine();System.out.print("Event Name: ");String name=sc.nextLine();
  System.out.print("Date (YYYY-MM-DD): ");LocalDate date=LocalDate.parse(sc.nextLine());System.out.print("Time (HH:MM): ");LocalTime time=LocalTime.parse(sc.nextLine());
  System.out.print("Venue: ");String venue=sc.nextLine();System.out.print("Capacity: ");int cap=Integer.parseInt(sc.nextLine());System.out.print("Description: ");String desc=sc.nextLine();
  if(eventDAO.addEvent(new Event(id,name,date,time,venue,cap,desc),o.getOrganizerId()))System.out.println("Event created successfully!");
 }
}
