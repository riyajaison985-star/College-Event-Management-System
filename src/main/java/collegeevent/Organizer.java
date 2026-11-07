package collegeevent;
public class Organizer extends Person {
 private String organizerId;
 public Organizer(String organizerId,String name){super(name);this.organizerId=organizerId;}
 public String getOrganizerId(){return organizerId;}
 @Override public void displayRole(){System.out.println("Role: Organizer");}
}
