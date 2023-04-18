import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginPanel extends JPanel{
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
                User user = manageUsers.login(usernameField.getText(), new String(idField.getText()));
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
