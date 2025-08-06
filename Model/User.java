import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// import authenticationInterface.*;

public class User implements Authentication {
    private int userId;
    private String fullName;
    private String email;
    private String contactNumber;
    private String password;
    private String createdAt;
    private String role; // 'A' for admin, 'E' for EventOrganizer, 'R' for registrant
    private String isVerified;

    // Static variables to store all users (class level data)
    protected static Map<String, User> allUsers = new HashMap<>(); // email -> User
    private static int nextUserId = 1;

    // Single constructor for new user signup
    public User(String fullName, String email, String password) {
        this.userId = nextUserId++;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.role = "R"; // Default role is registrant
        this.isVerified = "N"; // Not verified by default - only admin can verify
    }

    protected String getRole(){
        return "A".equals(role) ? "admin" : "registration";
    }
    // =============== Getter and Setter ===========
    
    public void setRole(String role) {
        if (!role.equals("A") && !role.equals("B")) {
            System.out.println("Invalid role! Only 'A' (admin) or 'U' (user) allowed.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine().trim();
        if (adminPassword.equals("admin123")) { // Hardcoded for simplicity
            this.role = role;
            System.out.println("Role set to " + getRole());
        } else {
            System.out.println("Invalid admin password!");
        }
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    

    public int getUserId() {
        return userId;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    private ArrayList<String> emails = new ArrayList<>();

    // Getter for registered emails
    public ArrayList<String> emailList() {
        return emails;
    }

    // ================== Main Action ===============

    // ===  User sign-up  ===
    public void signUp(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Invalid email format!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SIGN UP ===");

        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine().trim();

        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        User newUser = new User(fullName, email, password);
        newUser.setContactNumber(contactNumber);
        allUsers.put(email, newUser);

        System.out.println("Registration successful!");
        System.out.println("Welcome, " + newUser.getFullName());
        System.out.println("User ID: " + newUser.getUserId());
        System.out.println("You can now register for events or create events with a valid token.");
    }

    // ===  user login  ===
    public void login(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== LOGIN ===");

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = allUsers.get(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            System.out.println("Welcome back, " + user.getFullName() + "!");
        } else {
            System.out.println("Incorrect email or password. Please try again.");
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

}
