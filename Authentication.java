// Used to establish a connection to the database
import java.sql.Connection;
// Used to load the JDBC driver and establish a connection to the database
import java.sql.DriverManager;
// Used to create a prepared SQL statement that can be executed against the database
import java.sql.PreparedStatement;
// Used to store the result set returned by executing a SQL query
import java.sql.ResultSet;
// Used to represent a date and time value, needed to determine the current time
import java.time.LocalDateTime;
// Used to read user input from the console
import java.util.Scanner;

public class Authentication {
    // Database connection details
    private static final String DB_URL = "database";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public Authentication() {
    }

    // Authenticate the user based on username and ID
    public boolean authenticateUser(String username, int id) {
        // SQL query to select the user with the given username and ID
        String query = "SELECT * FROM users WHERE username = ? AND id = ?;";

        // Establish a database connection, create a PreparedStatement, and execute the query
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, id);

            // If a user is found in the ResultSet, authentication is successful
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (Exception e) {
            // Handle any exceptions that occur during database operations
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Get a custom welcome message based on the time of day
    private String getWelcomeMessage() {
        // Get the current hour of the day
        int hour = LocalDateTime.now().getHour();

        // Return an appropriate welcome message based on the hour
        if (hour >= 5 && hour < 12) {
            return "Good morning!";
        } else if (hour >= 12 && hour < 17) {
            return "Good afternoon!";
        } else if (hour >= 17 && hour < 21) {
            return "Good evening!";
        } else {
            return "Good night!";
        }
    }

    public static void main(String[] args) {
        // Create an Authentication object
        Authentication authentication = new Authentication();

        // Read user input for username and ID
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your ID: ");
            int id = scanner.nextInt();

            // Authenticate the user
            boolean isAuthenticated = authentication.authenticateUser(username, id);

            // Display appropriate messages based on the authentication result
            if (isAuthenticated) {
                System.out.println("Authentication successful!");
                System.out.println(authentication.getWelcomeMessage());
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during user input
            System.err.println("Error: " + e.getMessage());
        }
    }
}
