import java.util.List;
import java.util.ArrayList;
public class ManageUsers {
    private List<User> users;

    public ManageUsers() {
        users = new ArrayList<>();
    }

    public boolean register(String username, String id) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User newUser = new User(username, id);
        users.add(newUser);
        return true;
    }

    public User login(String username, String id) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getId().equals(id)) {
                return user; // Login successful
            }
        }
        return null; // Login failed
    }

    //TODO: add registration method

}
