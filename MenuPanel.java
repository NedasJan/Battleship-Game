import javax.swing.*;

public class MenuPanel extends JLabel {
    private JButton playButton = new BigButton(new ImageIcon("Graphics/Big_Icons/PlayIcon.png"));
    private JButton exitButton = new BigButton(new ImageIcon("Graphics/Big_Icons/ExitIcon.png"));

    public MenuPanel() {
        setupButtons();
        setupPanel();
    }

    private void setupButtons() {
        playButton.setLocation(490, 495);
        exitButton.setLocation(490, 570);
    }

    private void setupPanel() {
        setSize(1280, 720);
        setIcon(new ImageIcon("Graphics/Backgrounds/MenuBackground.png"));

        add(playButton);
        add(exitButton);
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
