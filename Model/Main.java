import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Event Registration System ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Create Event (Token Required)");
            System.out.println("4. Approve Event (Admin)");
            System.out.println("5. Display Active Events");
            System.out.println("6. Search Event");
            System.out.println("7. Update Event (Token Required)");
            System.out.println("8. Cancel Event (Token Required)");
            System.out.println("9. Delete Event (Admin)");
            System.out.println("10. Create Registration");
            System.out.println("11. Make Registration");
            System.out.println("12. Cancel Registration");
            System.out.println("13. View Registration List");
            System.out.println("14. Approve Registration (Admin)");
            System.out.println("15. View Approved Registrations");
            System.out.println("16. Add Tokens (Admin)");
            System.out.println("17. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: // Sign Up
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    User user = new User("", email, "");
                    user.signUp(email);
                    break;
                case 2: // Login
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    user = new User("", email, "");
                    user.login(email);
                    break;
                case 3: // Create Event
                    Event event = new Event(0, "", "", "", "", "", 0, 0, false, "");
                    event.createEvent();
                    break;
                case 4: // Approve Event
                    System.out.print("Enter Event ID: ");
                    int eventId = scanner.nextInt();
                    Event eventToApprove = Event.getAllEvents().get(eventId);
                    if (eventToApprove != null) {
                        eventToApprove.approveEvent();
                    } else {
                        System.out.println("Event not found!");
                    }
                    break;
                case 5: // Display Active Events
                    Event.displayNewEvent();
                    break;
                case 6: // Search Event
                    event = new Event(0, "", "", "", "", "", 0, 0, false, "");
                    event.searchEvent();
                    break;
                case 7: // Update Event
                    System.out.print("Enter Event ID: ");
                    eventId = scanner.nextInt();
                    event = Event.getAllEvents().get(eventId);
                    if (event != null) {
                        event.updateEvent();
                    } else {
                        System.out.println("Event not found!");
                    }
                    break;
                case 8: // Cancel Event
                    System.out.print("Enter Event ID: ");
                    eventId = scanner.nextInt();
                    event = Event.getAllEvents().get(eventId);
                    if (event != null) {
                        event.cancelEvent();
                    } else {
                        System.out.println("Event not found!");
                    }
                    break;
                case 9: // Delete Event
                    System.out.print("Enter Event ID: ");
                    eventId = scanner.nextInt();
                    event = Event.getAllEvents().get(eventId);
                    if (event != null) {
                        event.deleteEvent();
                    } else {
                        System.out.println("Event not found!");
                    }
                    break;
                case 10: // Create Registration
                    Registration reg = new Registration(0, 0, 0, "", "", false);
                    reg.createRegistrationForm();
                    break;
                case 11: // Make Registration
                    reg = new Registration(0, 0, 0, "", "", false);
                    reg.makeRegistration();
                    break;
                case 12: // Cancel Registration
                    reg = new Registration(0, 0, 0, "", "", false);
                    reg.cancelRegistration();
                    break;
                case 13: // View Registration List
                    reg = new Registration(0, 0, 0, "", "", false);
                    reg.viewRegistrationList();
                    break;
                case 14: // Approve Registration
                    reg = new Registration(0, 0, 0, "", "", false);
                    reg.approveRegistration();
                    break;
                case 15: // View Approved Registrations
                    reg = new Registration(0, 0, 0, "", "", false);
                    reg.viewListRegisterd();
                    break;
                case 16: // Add Tokens (Admin)
                    Admin admin = new Admin("", "", "", "", new ArrayList<>());
                    admin.inputTokenToList();
                    break;
                case 17: // Exit
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}