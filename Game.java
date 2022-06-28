import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements ActionListener, KeyListener {
    public static void main(String[] args) {
        new Game();
    }

    //-------------------------------------------------------

    private MenuPanel menuPanel = new MenuPanel();
    private GameModePanel gameModePanel = new GameModePanel();
    private YouWinPanel youWinPanel = new YouWinPanel();
    private GameOverPanel gameOverPanel = new GameOverPanel();

    private FindEnemyShips findEnemyShips = new FindEnemyShips();
    private PlayerVersusComputer playerVersusComputer = new PlayerVersusComputer();

    public Game() {
        setupButtons();
        setupFrame();
    }

    private void setupButtons() {
        menuPanel.getPlayButton().addActionListener(this);
        menuPanel.getExitButton().addActionListener(this);

        gameModePanel.getGoBackButton().addActionListener(this);
        gameModePanel.getFindEnemyShipsButton().addActionListener(this);
        gameModePanel.getPlayerVersusComputerButton().addActionListener(this);

        findEnemyShips.getGoBackButton().addActionListener(this);
        findEnemyShips.getGameWonSignal().addActionListener(this);
        findEnemyShips.getGameLostSignal().addActionListener(this);

        playerVersusComputer.getGoBackButton().addActionListener(this);
        playerVersusComputer.getGameWonSignal().addActionListener(this);
        playerVersusComputer.getGameLostSignalSignal().addActionListener(this);

        youWinPanel.getPlayAgainButton().addActionListener(this);
        youWinPanel.getExitButton().addActionListener(this);

        gameOverPanel.getPlayAgainButton().addActionListener(this);
        gameOverPanel.getExitButton().addActionListener(this);
    }

    private void setupFrame() {
        addKeyListener(this);

        setSize(1280, 720);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setTitle("BATTLESHIP");
        setIconImage(new ImageIcon("Graphics/Icon.png").getImage());
        setContentPane(menuPanel);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        Object source = action.getSource();

        if (source == menuPanel.getPlayButton()) {
            setContentPane(gameModePanel);
        }
        else if (source == menuPanel.getExitButton()) {
            System.exit(0);
        }

        if (source == gameModePanel.getGoBackButton()) {
            setContentPane(menuPanel);
        }
        else if (source == gameModePanel.getFindEnemyShipsButton()) {
            setContentPane(findEnemyShips);
        }
        else if (source == gameModePanel.getPlayerVersusComputerButton()) {
            setContentPane(playerVersusComputer);
        }

        if (source == findEnemyShips.getGoBackButton()) {
            setContentPane(gameModePanel);
            findEnemyShips = new FindEnemyShips();
            setupButtons();
        }
        else if (source == findEnemyShips.getGameWonSignal()) {
            setContentPane(youWinPanel);
            findEnemyShips = new FindEnemyShips();
            setupButtons();
        }
        else if (source == findEnemyShips.getGameLostSignal()) {
            setContentPane(gameOverPanel);
            findEnemyShips = new FindEnemyShips();
            setupButtons();
        }

        if (source == playerVersusComputer.getGoBackButton()) {
            setContentPane(gameModePanel);
            playerVersusComputer = new PlayerVersusComputer();
            setupButtons();
        }
        else if (source == playerVersusComputer.getGameWonSignal()) {
            setContentPane(youWinPanel);
            playerVersusComputer = new PlayerVersusComputer();
            setupButtons();
        }
        else if (source == playerVersusComputer.getGameLostSignalSignal()) {
            setContentPane(gameOverPanel);
            playerVersusComputer = new PlayerVersusComputer();
            setupButtons();
        }

        if (source == youWinPanel.getPlayAgainButton()) {
            setContentPane(gameModePanel);
        }
        else if (source == youWinPanel.getExitButton()) {
            System.exit(0);
        }

        if (source == gameOverPanel.getPlayAgainButton()) {
            setContentPane(gameModePanel);
        }
        else if (source == gameOverPanel.getExitButton()) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            playerVersusComputer.switchShipOrientation();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Unused;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Unused;
    }
}
