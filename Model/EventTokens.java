import java.util.ArrayList;
import java.util.Scanner;

public class EventTokens {
    private int tokenId;
    private String tokenString;
    private Boolean isUsed;
    private String createdAt;
    private String usedAt;
    private int createdBy;
    private int usedBy;
    private int usedCount;

    public EventTokens(int tokenId, String tokenString, Boolean isUsed, String createdAt, String usedAt, int createdBy,
            int usedBy, int usedCount) {
        this.tokenId = tokenId;
        this.tokenString = tokenString;
        this.isUsed = isUsed;
        this.createdAt = createdAt;
        this.usedAt = usedAt;
        this.createdBy = createdBy;
        this.usedBy = usedBy;
        this.usedCount = usedCount;
    }

    public String generateToken() {
        return tokenString;
    }

    public boolean verifyEventoken() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter event token: ");
        String inputToken = scan.nextLine();

        Admin admin = new Admin("", "", "", "", new ArrayList<>());
        return admin.isTokenValid(inputToken);
    }

}
