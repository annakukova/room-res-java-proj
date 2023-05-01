import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RoomReservationApp extends JFrame {

    private JTabbedPane tabs;
    private JPanel loginPanel, roomSelectionPanel, confirmationPanel;
    private JTextField usernameField, idField;
    private JComboBox<String> roomTypeBox, blockBox;
    private JTextField roommateField;
    private JButton assignRandomButton, confirmButton, loginButton, reserveButton;

    private JLabel usernameLabel, confirmationUsernameLabel, roomNumberLabel, roommateLabel;

    // Define an ArrayList to store the users
    private ArrayList<User> users = new ArrayList<>();

    // Define an ArrayList to store the reserved rooms
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public RoomReservationApp() {
        // Add some sample users to the ArrayList
        users.add(new User("123", "user1"));
        users.add(new User("456", "user2"));
        users.add(new User("789", "user3"));

        Color panelBackgroundColor = Color.decode("#242933");
        Color labelTextColor = Color.decode("#ffffff");
        Color buttonBackgroundColor = Color.decode("#1fb2a5");

        // Initialize the UI components
        tabs = new JTabbedPane();
        loginPanel = new JPanel();
        roomSelectionPanel = new JPanel();
        confirmationPanel = new JPanel();

        //Tabs for the app - Login, Room Selection, Confirmation
        tabs.addTab("Login", loginPanel);
        tabs.addTab("Room Selection", roomSelectionPanel);
        tabs.addTab("Confirmation", confirmationPanel);
        add(tabs);

        //Components of login
        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(labelTextColor);
        usernameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID Number:");
        idLabel.setForeground(labelTextColor);
        idField = new JTextField(20);

        loginButton = new JButton("Login");
        loginButton.setBackground(buttonBackgroundColor);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(80, 40));
        loginButton.setRolloverEnabled(true);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(idLabel);
        loginPanel.add(idField);
        loginPanel.add(loginButton);

        loginPanel.setBackground(panelBackgroundColor);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String username = usernameField.getText();

                // Check if the ID and Username are correct
                boolean validUser = false;
                for (User user : users) {
                    if (user.getId().equals(id) && user.getUsername().equals(username)) {
                        validUser = true;
                        break;
                    }
                }

                if (validUser) {
                    // Switch to the room selection panel
                    tabs.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(RoomReservationApp.this, "Invalid ID or Username!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Components of room selection
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setForeground(labelTextColor);
        String[] roomTypes = {"Double in Apartment", "Double", "Single"};
        roomTypeBox = new JComboBox<>(roomTypes);

        JLabel blockLabel = new JLabel("Block:");
        blockLabel.setForeground(labelTextColor);
        String[] blocks = {"A", "B", "C"};
        blockBox = new JComboBox<>(blocks);

        roommateLabel = new JLabel("Roommate:");
        roommateLabel.setForeground(labelTextColor);
        roommateField = new JTextField(20);
        assignRandomButton = new JButton("Assign Random");
        assignRandomButton.setBackground(buttonBackgroundColor);
        assignRandomButton.setOpaque(true);
        assignRandomButton.setBorderPainted(false);
        assignRandomButton.setFocusPainted(false);
        assignRandomButton.setPreferredSize(new Dimension(160, 40));
        assignRandomButton.setRolloverEnabled(true);
        assignRandomButton.setForeground(Color.WHITE);
        assignRandomButton.setFont(new Font("Arial", Font.BOLD, 14));
        assignRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get a random roommate from the users list
                if (users.size() > 1) {
                    Random random = new Random();
                    int index = random.nextInt(users.size());
                    User randomUser = users.get(index);
                    roommateField.setText(randomUser.getUsername());
                }
            }
        });

        reserveButton = new JButton("Reserve");
        reserveButton.setBackground(buttonBackgroundColor);
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setFocusPainted(false);
        reserveButton.setPreferredSize(new Dimension(120, 40));
        reserveButton.setRolloverEnabled(true);
        reserveButton.setForeground(Color.WHITE);
        reserveButton.setFont(new Font("Arial", Font.BOLD, 14));
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate a random room number
                Random random = new Random();
                int roomNumber = random.nextInt(100) + 1;

                // Create a new reservation and add it to the ArrayList
                Reservation reservation = new Reservation(usernameField.getText(), roomNumber, roommateField.getText(), roomTypeBox.getSelectedItem().toString(), blockBox.getSelectedItem().toString());
                reservations.add(reservation);

                // Switch to the confirmation panel
                tabs.setSelectedIndex(2);

                // Display the confirmation information in the confirmation panel
                confirmationUsernameLabel.setText("Username: " + reservation.getUsername());
                roomNumberLabel.setText("Room Number: " + reservation.getRoomNumber());
                roommateLabel.setText("Roommate: " + reservation.getRoommate());
            }
        });

        roomSelectionPanel.add(roomTypeLabel);
        roomSelectionPanel.add(roomTypeBox);
        roomSelectionPanel.add(blockLabel);
        roomSelectionPanel.add(blockBox);
        roomSelectionPanel.add(roommateLabel);
        roomSelectionPanel.add(roommateField);
        roomSelectionPanel.add(assignRandomButton);
        roomSelectionPanel.add(reserveButton);

        roomSelectionPanel.setBackground(panelBackgroundColor);

        //Components of confirmation panel
        confirmationUsernameLabel = new JLabel();
        confirmationUsernameLabel.setForeground(labelTextColor);
        JLabel confirmationLabel = new JLabel("Confirmation:");
        confirmationLabel.setForeground(labelTextColor);

        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(buttonBackgroundColor);
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmButton.setFocusPainted(false);
        confirmButton.setPreferredSize(new Dimension(120, 40));
        confirmButton.setRolloverEnabled(true);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomReservationApp.this.dispose();
            }
        });

        roomNumberLabel = new JLabel();
        roomNumberLabel.setForeground(labelTextColor);

        roommateLabel = new JLabel();
        roommateLabel.setForeground(labelTextColor);

        confirmationPanel.add(confirmationLabel);
        confirmationPanel.add(confirmationUsernameLabel);
        confirmationPanel.add(roomNumberLabel);
        confirmationPanel.add(roommateLabel);
        confirmationPanel.add(confirmButton);

        confirmationPanel.setBackground(panelBackgroundColor);

        setTitle("Room Reservation App");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RoomReservationApp app = new RoomReservationApp();
                app.setVisible(true);
            }
        });
    }

    class User {
        private String id;
        private String username;

        public User(String id, String username) {
            this.id = id;
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }
    }

    class Reservation {
        private String username;
        private int roomNumber;
        private String roommate;
        private String roomType;
        private String block;

        public Reservation(String username, int roomNumber, String roommate, String roomType, String block) {
            this.username = username;
            this.roomNumber = roomNumber;
            this.roommate = roommate;
            this.roomType = roomType;
            this.block = block;
        }

        public String getUsername() {
            return username;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getRoommate() {
            return roommate;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getBlock() {
            return block;
        }
    }
}




