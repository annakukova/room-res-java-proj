import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class RegistrationPanel extends JPanel{
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
                String id = new String(idField.getId());//TODO getId()
                String confirmId = new String(confirmIdField.getId());//TODO getId()
                if (!id.equals(confirmId)) {
                    errorLabel.setText("Passwords do not match");
                } else if (!manageUsers.register(username, id)) {
                    errorLabel.setText("Email already in use");
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
