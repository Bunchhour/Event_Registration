import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Event {
    private int eventId;
    private String title;
    private String description;
    private String location;
    private String startTime;
    private String endTime;
    private int creatorId;
    private int organizerId;
    private int capacity;
    private boolean eventStatus;
    private String createdAt;
    private static HashMap<Integer, Event> allEvents = new HashMap<>();
    private static int nextEventId =1;

    public Event(int eventId, String title, String description, String location, String startTime, String endTime,
                 int creatorId, int capacity, boolean eventStatus, String createdAt) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creatorId = creatorId;
        this.capacity = capacity;
        this.eventStatus = eventStatus;
        this.createdAt = createdAt;
        allEvents.put(eventId, this);
    }

    public String getTitle() {
        return title;
    }

    public boolean getEventStatus() {
        return eventStatus;
    }

    public static Map<Integer, Event> getAllEvents() {
        return allEvents;
    }

    public int getCreatorId(){
        return creatorId;
    }

    // Create Function
    public void createEvent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("=== Create New Event ===");
        
        System.out.print("Enter User ID: ");
        int creatorId = scan.nextInt();
        scan.nextLine();

        User user = User.allUsers.values().stream()
                .filter(u -> u.getUserId() == creatorId)
                .findFirst()
                .orElse(null);
        
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        // Validate event token
        EventTokens token = new EventTokens(1, "", false, "", "", 0, 0, 0);
        Admin admin = new Admin("", "", "", "", new ArrayList<>());
        System.out.print("Enter event token: ");
        String inputToken = scan.nextLine();
        if (!admin.isTokenValid(inputToken)) {
            System.out.println("Invalid event token! Cannot create event.");
            return;
        }

        System.out.print("Title: ");
        String title = scan.nextLine();
        System.out.print("Description: ");
        String description = scan.nextLine();
        System.out.print("Location: ");
        String location = scan.nextLine();
        System.out.print("Start time (yyyy-MM-dd HH:mm:ss): ");
        String startTime = scan.nextLine();
        System.out.print("End time (yyyy-MM-dd HH:mm:ss): ");
        String endTime = scan.nextLine();
        System.out.print("Capacity: ");
        int capacity = scan.nextInt();
        scan.nextLine();

        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Event event = new Event(nextEventId++, title, description, location, startTime, endTime, creatorId, capacity, false, createdAt);
        System.out.println("Event created successfully! Event ID: " + event.eventId + " (Pending admin approval)");
    }
    // Admin approve event
    public boolean approveEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.nextLine().trim();
        
        if (!adminPassword.equals("admin123")) {
            System.out.println("Invalid admin password!");
            return false;
        }
        
        this.eventStatus = true;
        System.out.println("Event approved successfully!");
        return true;
    }

    
    public static void displayNewEvent() {
        System.out.println("=== Active Events ===");
        boolean found = false;
        for (Event event : allEvents.values()) {
            if (event.eventStatus) {
                System.out.println("Event ID: " + event.eventId + ", Title: " + event.title +
                        ", Location: " + event.location + ", Start: " + event.startTime);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active events found.");
        }
    }

    public void cancelEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        if (userId != this.creatorId) {
            System.out.println("Only the event creator can cancel this event!");
            return;
        }
        
        Admin admin = new Admin("", "", "", "", new ArrayList<>());
        System.out.print("Enter event token: ");
        String inputToken = scanner.nextLine();
        if (!admin.isTokenValid(inputToken)) {
            System.out.println("Invalid event token! Cannot cancel event.");
            return;
        }
        
        this.eventStatus = false;
        System.out.println("Event cancelled successfully!");
    }


    public void deleteEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.nextLine().trim();
        
        if (!adminPassword.equals("admin123")) {
            System.out.println("Invalid admin password!");
            return;
        }
        
        allEvents.remove(this.eventId);
        System.out.println("Event deleted successfully!");
    }

    public void aboutEvent() {
        System.out.println("=== Event Details ===");
        System.out.println("Event ID: " + eventId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Creator ID: " + creatorId);
        System.out.println("Capacity: " + capacity);
        System.out.println("Status: " + (eventStatus ? "Active" : "Inactive"));
        System.out.println("Created At: " + createdAt);
    }

    public void updateEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        if (userId != this.creatorId) {
            System.out.println("Only the event creator can update this event!");
            return;
        }
        
        Admin admin = new Admin("", "", "", "", new ArrayList<>());
        System.out.print("Enter event token: ");
        String inputToken = scanner.nextLine();
        if (!admin.isTokenValid(inputToken)) {
            System.out.println("Invalid event token! Cannot update event.");
            return;
        }
        
        System.out.println("=== Update Event ===");
        System.out.print("New Title (current: " + title + "): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) this.title = newTitle;
        
        System.out.print("New Description (current: " + description + "): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) this.description = newDescription;
        
        System.out.print("New Location (current: " + location + "): ");
        String newLocation = scanner.nextLine();
        if (!newLocation.isEmpty()) this.location = newLocation;
        
        System.out.print("New Start Time (current: " + startTime + "): ");
        String newStartTime = scanner.nextLine();
        if (!newStartTime.isEmpty()) this.startTime = newStartTime;
        
        System.out.print("New End Time (current: " + endTime + "): ");
        String newEndTime = scanner.nextLine();
        if (!newEndTime.isEmpty()) this.endTime = newEndTime;
        
        System.out.print("New Capacity (current: " + capacity + "): ");
        String newCapacity = scanner.nextLine();
        if (!newCapacity.isEmpty()) this.capacity = Integer.parseInt(newCapacity);
        
        System.out.println("Event updated successfully!");
    }
    public void searchEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the event ID: ");
        int inputId = scanner.nextInt();
        
        Event event = allEvents.get(inputId);
        if (event != null) {
            event.aboutEvent();
        } else {
            System.out.println("Event not found.");
        }
    }

}