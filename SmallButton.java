import javax.swing.*;

public class SmallButton extends JButton {
    public SmallButton() {
        setSize(100, 50);
    }
    public SmallButton(ImageIcon icon) {
        setSize(100, 50);
        setIcon(icon);
        setFocusable(false);
    }
}
