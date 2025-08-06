import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Registration {
    private int registrationId;
    private int userId;
    private int eventId;
    private String registrationDate;
    private String checkinTime;
    private boolean status;
    private static int nextRegistrationId = 1;
    private static HashMap<Integer, Registration> allRegistrations = new HashMap<>();

    public Registration(int registrationId, int userId, int eventId, String registrationDate, String checkinTime,
            boolean status) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.eventId = eventId;
        this.registrationDate = registrationDate;
        this.checkinTime = checkinTime;
        this.status = status;
    }

    public void createRegistrationForm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Create Registration Form===");

        System.out.print("Enter User ID: " );
        int userId = scanner.nextInt();
        System.out.print("Enter the event ID: ");
        scanner.nextLine();
        User user = User.allUsers.values().stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst()
                .orElse(null);
        Event event = Event.getAllEvents().get(eventId);

        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        if (event == null) {
            System.out.println("Event not found!");
            return;
        }
        if (!user.getRole().equals("registrant")) {
            System.out.println("Only registrants can register for events!");
            return;
        }
        if (!event.getEventStatus()) {
            System.out.println("Event is not active!");
            return;
        }
        String registrationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Registration registration = new Registration(nextRegistrationId++, userId, eventId, registrationDate, null, false);
        allRegistrations.put(registration.registrationId, registration);
        System.out.println("Registration created successfully! Registration ID: " + registration.registrationId);

    }

    public void makeRegistration(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Make Registration ===");
        System.out.println("Enter Registration ID: ");
        int regId = scanner.nextInt();

        Registration reg = allRegistrations.get(regId);
        if (reg == null) {
            System.out.println("Registration not found!");
            return;
        }
        if (reg.status) {
            System.out.println("Registration already approved!");
            return;
        }
        
    }

    public void cancelRegistration(){
        
    }

    public void viewRegistrationList(){
        
    }

    // Admin approve user registration
    public void approveRegistration(){

    }

    public void viewListRegisterd(){

    }

    
}
