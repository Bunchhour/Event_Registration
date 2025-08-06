import java.util.ArrayList;
import java.util.Scanner;

// import authenticationInterface.Authentication;

public class Admin extends User {
    private String adminLevel;
    private ArrayList<String> tokenList;

    public Admin(String fullName, String email, String password, String adminLevel, ArrayList<String> tokenList) {
        super(fullName, email, password);
        this.adminLevel = adminLevel;
        this.tokenList = tokenList;
    }

    @Override
    public void signUp(String email) {
        super.signUp(email);
        this.setRole("A");
        System.out.println("Sign up as: " + this.getRole());
    }

    @Override
    public void login(String email) {
        super.signUp(email);
        this.setRole("A");
        System.out.println("Login as: " + this.getRole());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + adminLevel;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public ArrayList<String> inputTokenToList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of tokens to add: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter token #" + (i + 1) + ": ");
            String token = scanner.nextLine();
            tokenList.add(token);
        }

        System.out.println("Tokens added successfully!");
        return tokenList;
    }

    public boolean isTokenValid(String inputToken) {
        return tokenList.contains(inputToken);
    }


}
