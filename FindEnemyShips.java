//GameMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindEnemyShips extends JLabel implements ActionListener {
    private final int SHOT_COUNT = 12;

    private BotMap botMap = new BotMap();

    private StatPanel statPanel = new StatPanel(SHOT_COUNT, botMap.getCombinedShipHealth());
    private GridPanel gridPanel = new GridPanel();

    private JButton goBackButton = new GoBackButton();
    private JButton gameWonSignal = new JButton();
    private JButton gameLostSignal = new JButton();

    public FindEnemyShips() {
        setupStatPanel();
        setupGridPanel();
        setupPanel();
    }

    private void setupStatPanel() {
        statPanel.setLocation(390, 0);
    }

    private void setupGridPanel() {
        gridPanel.setLocation(390, 130);

        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                gridPanel.getTile(row, column).addActionListener(this);
            }
        }
    }

    private void setupPanel() {
        setSize(1280, 720);
        setIcon(new ImageIcon("Graphics/Backgrounds/Background.png"));

        add(goBackButton);
        add(gridPanel);
        add(statPanel);
    }

    private void processClick(int row, int column) {
        gridPanel.getTile(row, column).setEnabled(false);

        if (botMap.getTile(row, column) == 1) {
            gridPanel.getTile(row, column).setDisabledIcon(gridPanel.getHitIcon());

            statPanel.incrementShotCount();
            statPanel.reduceHealth();

            if (statPanel.getHealth() == 0) {
                gameWonSignal.doClick();
            }
        }
        else {
            gridPanel.getTile(row, column).setDisabledIcon(gridPanel.getMissIcon());

            statPanel.reduceShotCount();

            if (statPanel.getShotCount() == 0) {
                gameLostSignal.doClick();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();

        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                if (source == gridPanel.getTile(row, column)) {
                    processClick(row, column);
                    break;
                }
            }
        }
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getGameWonSignal() {
        return gameWonSignal;
    }

    public JButton getGameLostSignal() {
        return gameLostSignal;
    }
}