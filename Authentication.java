import java.util.Scanner;

public class Authentication {
    private String[] usernames;    // Array to store usernames
    private String[] passwords;    // Array to store passwords
    private String[] roles;        // Array to store roles

    public Authentication() {
        // Initialize arrays to store user information
        usernames = new String[]{"student1", "faculty1", "admin1"};
        passwords = new String[]{"password1", "password2", "password3"};
        roles = new String[]{"STUDENT", "FACULTY", "ADMIN"};
    }

    // Authenticates a user based on username and password
    public boolean authenticateUser(String username, String password) {
        int index = getUserIndex(username);
        if (index != -1 && passwords[index].equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // Gets the role of a user
    public String getUserRole(String username) {
        int index = getUserIndex(username);
        if (index != -1) {
            return roles[index];
        } else {
            return null;
        }
    }

    // Gets the index of a user in the usernames array
    private int getUserIndex(String username) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Create an instance of Authentication
        Authentication authentication = new Authentication();

        // Read user input for username and password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate user
        boolean isAuthenticated = authentication.authenticateUser(username, password);

        if (isAuthenticated) {
            System.out.println("Authentication successful!");
            String userRole = authentication.getUserRole(username);
            System.out.println("User role: " + userRole);
        } else {
            System.out.println("Authentication failed.");
        }
    }
}
