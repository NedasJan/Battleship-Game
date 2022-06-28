//GameMode

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerVersusComputer extends JLabel implements ActionListener, MouseListener {
    private int shipsPlaced = 0;
    private boolean placeHorizontally = true;
    private boolean canBePlaced = false;

    private Map playerMap = new Map();
    private Map botMap = new BotMap();
    private Bot bot;

    private GridPanel playerGridPanel = new PlayerGridPanel();
    private GridPanel botGridPanel = new GridPanel();

    private JLabel playerLabel = new TextLabel();
    private JLabel botLabel = new TextLabel();

    private JButton autoPlaceButton = new SmallButton(new ImageIcon("Graphics/Small_Icons/AutoIcon.png"));
    private JButton resetButton = new SmallButton(new ImageIcon("Graphics/Small_Icons/ResetIcon.png"));
    private JButton readyButton = new SmallButton(new ImageIcon("Graphics/Small_Icons/ReadyIcon.png"));

    private JButton goBackButton = new GoBackButton();
    private JButton gameWonSignal = new JButton();
    private JButton gameLostSignal = new JButton();

    public PlayerVersusComputer() {
        setupButtons();
        setupPlayerGridPanel();
        setupBotGridPanel();
        setupPlayerLabel();
        setupBotLabel();
        setupPanel();
    }

    private void setupButtons() {
        autoPlaceButton.setLocation(900, 25);
        autoPlaceButton.addActionListener(this);

        resetButton.setLocation(1030, 25);
        resetButton.addActionListener(this);

        readyButton.setLocation(1155, 25);
        readyButton.addActionListener(this);
    }

    private void setupPlayerLabel() {
        playerLabel.setLocation(50, 50);
        playerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        playerLabel.setText("PRESS R TO ROTATE / LEFTCLICK TO PLACE");
    }

    private void setupPlayerGridPanel() {
        playerGridPanel.setLocation(50, 130);

        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                playerGridPanel.getTile(row, column).addActionListener(this);
                playerGridPanel.getTile(row, column).addMouseListener(this);
                playerGridPanel.getTile(row, column).setFocusable(false);
            }
        }
    }

    private void setupBotLabel() {
        botLabel.setLocation(710, 50);
        botLabel.setText("BOT");
    }

    private void setupBotGridPanel() {
        botGridPanel.setLocation(710, 130);

        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                botGridPanel.getTile(row, column).addActionListener(this);
                botGridPanel.getTile(row, column).setFocusable(false);
            }
        }
    }

    private void setupPanel() {
        add(goBackButton);
        add(autoPlaceButton);
        add(resetButton);
        add(readyButton);
        add(playerLabel);
        add(playerGridPanel);
        add(botLabel);
        add(botGridPanel);

        setSize(1280, 720);
        setIcon(new ImageIcon("Graphics/Backgrounds/Background.png"));
        setFocusable(false);
    }

    private void processPlayerClick(int row, int column) {
        if (botMap.getTile(row, column) == 1) {
            int[] botAction = bot.getBotAction();

            botMap.reduceHealth();

            if (botMap.getCombinedShipHealth() == 0) {
                gameWonSignal.doClick();
            }

            botGridPanel.getTile(row, column).setDisabledIcon(botGridPanel.getHitIcon());
            botGridPanel.getTile(row, column).setEnabled(false);

            if (playerMap.getTile(botAction[0], botAction[1]) == 1) {
                playerMap.reduceHealth();
                playerGridPanel.setHitIcon(botAction[0], botAction[1]);
            }
            else {
                playerGridPanel.setMissIcon(botAction[0], botAction[1]);
            }
        }
        else {
            int[] botAction = bot.getBotAction();

            botGridPanel.getTile(row, column).setDisabledIcon(botGridPanel.getMissIcon());
            botGridPanel.getTile(row, column).setEnabled(false);

            if (playerMap.getTile(botAction[0], botAction[1]) == 1) {
                playerMap.reduceHealth();
                playerGridPanel.setHitIcon(botAction[0], botAction[1]);
            }
            else {
                playerGridPanel.setMissIcon(botAction[0], botAction[1]);
            }
        }

        if (playerMap.getCombinedShipHealth() == 0) {
            gameLostSignal.doClick();
        }
    }

    private void showShips() {
        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                if (playerMap.getTile(row, column) == 1) {
                    playerGridPanel.setHitIcon(row, column);
                }
                else {
                    playerGridPanel.setWaterIcon(row, column);
                }
            }
        }
    }

    public void switchShipOrientation() {
        placeHorizontally = !placeHorizontally;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (shipsPlaced < playerMap.getShipCount()) {
            Object source = e.getSource();

            for (int row = 0; row < Map.SIZE; row++) {
                for (int column = 0; column < Map.SIZE; column++) {
                    if (source == playerGridPanel.getTile(row, column)) {
                        if (shipsPlaced < playerMap.getShipCount()) {
                            int shipLength = playerMap.getShip(shipsPlaced).getLength();

                            if (placeHorizontally) {
                                if (column + shipLength <= 10) {
                                    canBePlaced = true;

                                    for (int k = column; k < column + shipLength; k++) {
                                        playerGridPanel.setHitIcon(row, k);

                                        if (playerMap.getTile(row, k) == 1) {
                                            canBePlaced = false;
                                        }
                                    }
                                }
                            }
                            else {
                                if (row + shipLength <= 10) {
                                    canBePlaced = true;

                                    for (int k = row; k < row + shipLength; k++) {
                                        playerGridPanel.setHitIcon(k, column);

                                        if (playerMap.getTile(k, column) == 1) {
                                            canBePlaced = false;
                                        }
                                    }
                                }
                            }
                        }

                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (shipsPlaced < playerMap.getShipCount()) {
            for (int row = 0; row < Map.SIZE; row++) {
                for (int column = 0; column < Map.SIZE; column++) {
                    if (playerMap.getTile(row, column) == 0) {
                        playerGridPanel.setWaterIcon(row, column);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Unused;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Unused;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Unused;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();

        if (shipsPlaced < playerMap.getShipCount()) {
            for (int row = 0; row < Map.SIZE; row++) {
                for (int column = 0; column < Map.SIZE; column++) {
                    if (source == playerGridPanel.getTile(row, column)) {
                        if (canBePlaced) {
                            if (placeHorizontally) {
                                    for (int k = column; k < column + playerMap.getShip(shipsPlaced).getLength(); k++) {
                                        playerMap.setTile(row, k);
                                        playerGridPanel.getTile(row, k).removeMouseListener(this);
                                    }

                            }
                            else {
                                for (int k = row; k < row + playerMap.getShip(shipsPlaced).getLength(); k++) {
                                    playerMap.setTile(k, column);
                                    playerGridPanel.getTile(k, column).removeMouseListener(this);
                                }

                            }
                            shipsPlaced++;
                        }

                        break;
                    }
                }
            }
        }
        else {
            for (int row = 0; row < Map.SIZE; row++) {
                for (int column = 0; column < Map.SIZE; column++) {
                    if (source == botGridPanel.getTile(row, column)) {
                        processPlayerClick(row, column);
                        break;
                    }
                }
            }
        }

        if (source == autoPlaceButton) {
            playerMap = new BotMap();
            showShips();
            shipsPlaced = playerMap.getShipCount();
        }
        else if (source == resetButton) {
            playerMap = new Map();
            showShips();
            shipsPlaced = 0;
        }
        else if (source == readyButton) {
            if (shipsPlaced == playerMap.getShipCount()) {
                for (int row = 0; row < Map.SIZE; row++) {
                    for (int column = 0; column < Map.SIZE; column++) {
                        if (playerGridPanel.getTile(row, column).getIcon() == playerGridPanel.getHitIcon()) {
                            playerGridPanel.setWaterIcon(row, column);
                        }
                    }
                }

                bot = new Bot(playerMap.getGrid());

                playerLabel.setFont(new Font("Serif", Font.BOLD, 30));
                playerLabel.setText("PLAYER");

                remove(autoPlaceButton);
                remove(resetButton);
                remove(readyButton);

                revalidate();
            }
        }
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getGameWonSignal() {
        return gameWonSignal;
    }

    public JButton getGameLostSignalSignal() {
        return gameLostSignal;
    }
}
