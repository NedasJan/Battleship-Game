import javax.swing.*;

public class GameModePanel extends JLabel {
    private JButton findEnemyShipsButton = new BigButton(new ImageIcon("Graphics/Big_Icons/FindEnemyShipsIcon.png"));
    private JButton playerVersusBotButton = new BigButton(new ImageIcon("Graphics/Big_Icons/PlayerVsBotIcon.png"));
    private JButton goBackButton = new GoBackButton();

    public GameModePanel() {
        setupButtons();
        setupPanel();
    }

    private void setupButtons() {
        findEnemyShipsButton.setLocation(490, 495);
        playerVersusBotButton.setLocation(490, 570);
    }

    private void setupPanel() {
        setSize(1280, 720);
        setIcon(new ImageIcon("Graphics/Backgrounds/GameModeBackground.png"));

        add(goBackButton);
        add(findEnemyShipsButton);
        add(playerVersusBotButton);
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getFindEnemyShipsButton() {
        return findEnemyShipsButton;
    }

    public JButton getPlayerVersusComputerButton() {
        return playerVersusBotButton;
    }
}
