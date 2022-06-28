import javax.swing.*;

public class StatPanel extends JLabel {
    private int shotCount;
    private int health;

    private JLabel shotsLeftPanel = new TextLabel();
    private JLabel enemyHealthPanel = new TextLabel();

    public StatPanel(int shotCount, int health) {
        this.health = health;
        this.shotCount = shotCount;

        setupPanel();
    }

    private void setupPanel() {
        setSize(500, 200);

        setupShotsLeftPanel();
        setupEnemyHealthPanel();

        add(shotsLeftPanel);
        add(enemyHealthPanel);
    }

    private  void setupShotsLeftPanel() {
        shotsLeftPanel.setText("SHOTS LEFT: " + shotCount);
    }

    private void setupEnemyHealthPanel() {
        enemyHealthPanel.setLocation(0, 50);
        enemyHealthPanel.setText("ENEMY HEALTH: " + health);
    }

    private void updateStatField() {
        shotsLeftPanel.setText("SHOTS LEFT: " + shotCount);
        enemyHealthPanel.setText("ENEMY HEALTH: " + health);
    }

    public void incrementShotCount() {
        shotCount++;
        updateStatField();
    }

    public void reduceShotCount() {
        shotCount--;
        updateStatField();
    }

    public void reduceHealth() {
        health--;
        updateStatField();
    }

    public int getHealth() {
        return health;
    }

    public int getShotCount() {
        return shotCount;
    }
}
