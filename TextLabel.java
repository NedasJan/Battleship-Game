import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {
    public TextLabel() {
        setSize(500, 100);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 30));
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setFocusable(false);
    }
}
