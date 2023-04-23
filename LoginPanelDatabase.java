import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.Shape;
import java.util.*;


public class RoomReservation extends JFrame {
    private static final long serialVersionUID = 1L;

    private ManageUsers manageUsers;

    public RoomReservation() {
        super("Room Reservation Skaptopara");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        manageUsers = new ManageUsers();

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel loginPanel = new LoginPanel(manageUsers);
        tabbedPane.addTab("Login", loginPanel);
        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RoomReservation();
    }

    public class User {
        private String username;
        private String id;

        public String getUsername() {
            return username;
        }

        public String getId() {
            return id;
        }

        public void setUsername() {
            this.username = username;
        }

        public void setId() {
            this.id = id;
        }

        public User(String username, String id) {
            this.username = username;
            this.id = id;
        }
    }

    public class RegistrationPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private ManageUsers manageUsers;
        private JTextField usernameField;
        private JTextField idField;
        private JTextField confirmIdField;
        private JLabel errorLabel;

        public RegistrationPanel(ManageUsers manageUsers) {
            this.manageUsers = manageUsers;

            JLabel usernameLabel = new JLabel("Username:");
            usernameField = new JTextField(20);

            JLabel idLabel = new JLabel("ID:");
            idField = new JTextField(20);

            JLabel confirmIdLabel = new JLabel("Confirm ID:");
            confirmIdField = new JTextField(20);

            JButton registerButton = new JButton("Register");
            registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String id = idField.getText();
                    String confirmId = confirmIdField.getText();
                    if (!id.equals(confirmId)) {
                        errorLabel.setText("IDs do not match");
                    } else if (!manageUsers.register(username, id)) {
                        errorLabel.setText("Username already in use");
                    } else {
                        errorLabel.setText("Registration successful");
                        usernameField.setText("");
                        idField.setText("");
                        confirmIdField.setText("");
                    }
                }
            });

            errorLabel = new JLabel();

            add(usernameLabel);
            add(usernameField);
            add(idLabel);
            add(idField);
            add(confirmIdLabel);
            add(confirmIdField);
            add(registerButton);
            add(errorLabel);
        }
    }

    public class ManageUsers {
        private List<User> users;
        private Authentication authentication;
        private void loadUsers() {
            String query = "SELECT * FROM users;";

            try (Connection connection = DriverManager.getConnection(Authentication.DB_URL, Authentication.DB_USER, Authentication.DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String username = resultSet.getString("username");
                        User user = new User(username, id);
                        users.add(user);
                    }
                }

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        public ManageUsers() {
            users = new ArrayList<>();
            authentication = new Authentication();
            loadUsers();
        }

        public boolean register(String username, String id) {
            // Check if the user already exists in the database
            if (authentication.authenticateUser(username, id)) {
                return false;
            }

            // Add the new user to the database
            boolean success = authentication.registerUser(username, id);
            if (success) {
                User newUser = new User(username, id);
                users.add(newUser);
            }
            return success;
        }

        public User login(String username, String id) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getId().equals(id)) {
                    return user; // Login successful
                }
            }
            return null; // Login failed
        }
    }

    public class LoginPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private ManageUsers manageUsers;
        private JTextField usernameField;
        private JTextField idField;
        private JLabel errorLabel;

        public LoginPanel(ManageUsers manageUsers) {
            this.manageUsers = manageUsers;

            JLabel usernameLabel = new JLabel("Username:");
            usernameField = new JTextField(20);

            JLabel idLabel = new JLabel("Student ID:");
            idField = new JTextField(20);

            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    User user = manageUsers.login(usernameField.getText(), idField.getText());
                    if (user == null) {
                        errorLabel.setText("Incorrect username or student ID");
                    } else {
                        // TODO: make it switch to the reservation panel here
                    }
                }
            });

            errorLabel = new JLabel();
            add(usernameLabel);
            add(usernameField);
            add(idLabel);
            add(idField);
            add(loginButton);
            add(errorLabel);
        }
    }


    public class Authentication {
        public static final String DB_URL = "jdbc:mysql://localhost:3306/room_database";
        public  static final String DB_USER = "root";
        public static final String DB_PASSWORD = "1234qwer";

        public Authentication() {
        }

        public boolean authenticateUser(String username, String id) {
            String query = "SELECT * FROM users WHERE username = ? AND id = ?;";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                return false;
            }
        }

        public boolean registerUser(String username, String id) {
            String query = "INSERT INTO users (id, username) VALUES (?, ?);";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, id);
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected == 1;

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                return false;
            }
        }
    }
}
