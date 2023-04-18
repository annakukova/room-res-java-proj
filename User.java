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
