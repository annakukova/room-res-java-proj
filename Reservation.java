
import java.util.Date;

public class Reservation {
    private String id;
    private String name;
    private String username;
    private Date checkinDate;
    private Date checkoutDate;
    private int roomNumber;
    private String roommate;

    public Reservation(String id, String name, String username, Date checkinDate, Date checkoutDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoommate() {
        return roommate;
    }

    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }
}
