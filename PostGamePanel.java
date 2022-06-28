import javax.swing.*;

public class PostGamePanel extends JLabel {
    private JButton playAgainButton = new BigButton(new ImageIcon("Graphics/Big_Icons/PlayAgainIcon.png"));
    private JButton exitButton = new BigButton(new ImageIcon("Graphics/Big_Icons/ExitIcon.png"));

    public PostGamePanel() {
        setupButtons();
        setupPanel();
    }

    private void setupButtons() {
        playAgainButton.setLocation(490, 495);
        exitButton.setLocation(490, 570);
    }

    private void setupPanel() {
        add(playAgainButton);
        add(exitButton);

        setSize(1280, 720);
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}