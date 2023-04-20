import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.Shape;
import java.util.*;
public class RoomReservationApp extends JFrame implements ActionListener{

    private JTabbedPane tabs;
    private JPanel loginPanel, roomSelectionPanel, confirmationPanel, adminPanel;
    private JTextField usernameField, idField;
    private JSpinner checkinSpinner, checkoutSpinner;
    private JComboBox<String> roomTypeBox, blockBox;
    private JTextField roommateField;
    private JButton assignRandomButton, confirmButton, loginButton, reserveButton, backToLoginButton;

    private JLabel usernameLabel, confirmationUsernameLabel, idLabel, roomNumberLabel, roommateLabel;
    private JList<String> roomList;
    private DefaultListModel<String> roomListModel;

    public RoomReservationApp() {
        Color panelBackgroundColor = Color.decode("#242933");//color for panel background
        Color labelTextColor = Color.decode("#ffffff");//color for text in labels
        Color buttonBackgroundColor = Color.decode("#1fb2a5");//color for button background

        // Initialize the UI components
        tabs = new JTabbedPane();
        loginPanel = new JPanel();
        roomSelectionPanel = new JPanel();
        confirmationPanel = new JPanel();
        adminPanel = new JPanel();

        //Tabs for the app - Login, Room Selection, Confirmation, Admin
        tabs.addTab("Login", loginPanel);
        tabs.addTab("Room Selection", roomSelectionPanel);
        tabs.addTab("Confirmation", confirmationPanel);
        tabs.addTab("Admin", adminPanel);
        add(tabs);

        //Components of login
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(labelTextColor);
        usernameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(labelTextColor);
        idField = new JTextField(20);

        JLabel checkinLabel = new JLabel("Check-in Date:");
        checkinLabel.setForeground(labelTextColor);
        SpinnerDateModel checkinModel = new SpinnerDateModel();
        checkinSpinner = new JSpinner(checkinModel);
        JSpinner.DateEditor checkinEditor = new JSpinner.DateEditor(checkinSpinner, "MM/dd/yyyy");
        checkinSpinner.setEditor(checkinEditor);

        JLabel checkoutLabel = new JLabel("Check-out Date:");
        checkoutLabel.setForeground(labelTextColor);
        SpinnerDateModel checkoutModel = new SpinnerDateModel();
        checkoutSpinner = new JSpinner(checkoutModel);
        JSpinner.DateEditor checkoutEditor = new JSpinner.DateEditor(checkoutSpinner, "MM/dd/yyyy");
        checkoutSpinner.setEditor(checkoutEditor);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(buttonBackgroundColor);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(80, 40));
        loginButton.setRolloverEnabled(true);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        //loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        //loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //loginButton.setMargin(new Insets(0, 0, 0, 0));


        loginButton.addActionListener(this);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(idLabel);
        loginPanel.add(idField);
        loginPanel.add(checkinLabel);
        loginPanel.add(checkinSpinner);
        loginPanel.add(checkoutLabel);
        loginPanel.add(checkoutSpinner);
        loginPanel.add(loginButton);



        loginPanel.setBackground(panelBackgroundColor); //set background color of panel

        //Components of room reservation panel
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setForeground(labelTextColor);
        String[] roomTypes = {"Double in Apartment", "Double", "Single"};
        roomTypeBox = new JComboBox<>(roomTypes);

        JLabel blockLabel = new JLabel("Block:");
        blockLabel.setForeground(labelTextColor);
        String[] blocks = {"A", "B", "C"};
        blockBox = new JComboBox<>(blocks);

        JLabel roommateLabel = new JLabel("Roommate:");
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
        assignRandomButton.addActionListener(this);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.setBackground(buttonBackgroundColor);
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setFocusPainted(false);
        reserveButton.setPreferredSize(new Dimension(120, 40));
        reserveButton.setRolloverEnabled(true);
        reserveButton.setForeground(Color.WHITE);
        reserveButton.setFont(new Font("Arial", Font.BOLD, 14));
        reserveButton.addActionListener(this);

        roomSelectionPanel.add(roomTypeLabel);
        roomSelectionPanel.add(roomTypeBox);
        roomSelectionPanel.add(blockLabel);
        roomSelectionPanel.add(blockBox);
        roomSelectionPanel.add(roommateLabel);
        roomSelectionPanel.add(roommateField);
        roomSelectionPanel.add(assignRandomButton);
        roomSelectionPanel.add(reserveButton);

        roomSelectionPanel.setBackground(panelBackgroundColor); //set background color of panel

        //Components of confirmation panel
        JLabel confirmationUsernameLabel = new JLabel("Username:");
        confirmationUsernameLabel.setForeground(labelTextColor);
        idLabel = new JLabel("ID:");
        idLabel.setForeground(labelTextColor);
        roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setForeground(labelTextColor);
        roommateLabel = new JLabel("Roommate:");
        roommateLabel.setForeground(labelTextColor);
        JLabel confirmationLabel = new JLabel("Confirmation:");
        confirmationLabel.setForeground(labelTextColor);

        JButton backToLoginButton = new JButton("Back to login");
        backToLoginButton = new JButton("Back to login");
        backToLoginButton.setBackground(buttonBackgroundColor);
        backToLoginButton.setOpaque(true);
        backToLoginButton.setBorderPainted(false);
        backToLoginButton.setFocusPainted(false);
        backToLoginButton.setPreferredSize(new Dimension(200, 40));
        backToLoginButton.setRolloverEnabled(true);
        backToLoginButton.setForeground(Color.WHITE);
        backToLoginButton.setFont(new Font("Arial", Font.BOLD, 14));

        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(buttonBackgroundColor);
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmButton.setFocusPainted(false);
        confirmButton.setPreferredSize(new Dimension(120, 40));
        confirmButton.setRolloverEnabled(true);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));

        confirmButton.addActionListener(this);

        confirmationPanel.add(confirmationLabel);
        confirmationPanel.add(confirmationUsernameLabel);
        confirmationPanel.add(idLabel);
        confirmationPanel.add(roomNumberLabel);
        confirmationPanel.add(roommateLabel);
        confirmationPanel.add(confirmButton);
        confirmationPanel.add(backToLoginButton);

        confirmationPanel.setBackground(panelBackgroundColor); //set background color of panel

        //Components of admin panel
        JLabel adminLabel = new JLabel("Admin:");
        adminLabel.setForeground(labelTextColor);
        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);
        JScrollPane scrollPane = new JScrollPane(roomList);
        adminPanel.add(adminLabel);
        adminPanel.add(scrollPane);

        adminPanel.setBackground(panelBackgroundColor); //set background color of panel

        setTitle("Room Reservation App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Get the input values from the login panel
            String username = usernameField.getText();
            String id = idField.getText();
            String checkinDate = (String) checkinSpinner.getValue(); //TODO: check if this gets the date correctly
            String checkoutDate = (String) checkoutSpinner.getValue(); //TODO: check if this gets the date correctly
            // TODO: switch to the room selection panel
        }
        else if (e.getSource() == assignRandomButton) {
            roommateField.setText("Anna Kukova");//TODO: Here, instead of a certain value, get random name from database or text file with math.random()
        } else if (e.getSource() == confirmButton) {
            confirmationUsernameLabel.setText("Username: " + usernameField);//does not work yet
            // TODO: Display the confirmation information in the confirmation panel
        } else if (e.getSource() == reserveButton) {
            // TODO: Reserve the selected room
        }
    }
    public static void main(String[] args) {
        new RoomReservationApp();
    }
}