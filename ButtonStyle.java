import javax.swing.*;
import java.awt.*;

public class ButtonStyle extends JButton {
    public ButtonStyle() {
        super();
        setStyle(this);
    }

    public ButtonStyle(String text) {
        super(text);
        setStyle(this);
    }

    public static void setStyle(JButton button) {
        Color buttonBackgroundColor = Color.decode("#1fb2a5"); // color for button background
        button.setBackground(buttonBackgroundColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.setRolloverEnabled(true);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}
