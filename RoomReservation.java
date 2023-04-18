import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
public class RoomReservation extends JFrame{
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
}