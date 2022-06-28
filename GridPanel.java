import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private JButton[][] tiles = new JButton[10][10];

    protected ImageIcon waterIcon = new ImageIcon("Graphics/Grid_Icons/WaterIcon.png");
    protected ImageIcon hitIcon = new ImageIcon("Graphics/Grid_Icons/RedHitIcon.png");
    protected ImageIcon missIcon =new ImageIcon("Graphics/Grid_Icons/RedMissIcon.png");

    public GridPanel() {
        setupPanel();
    }

    private void setupPanel() {
        setSize(500, 500);
        setLayout(new GridLayout(10, 10));

        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                tiles[row][column] = new JButton(waterIcon);
                add(tiles[row][column]);
            }
        }
    }

    public JButton getTile(int row, int column) {
        return tiles[row][column];
    }

    public void setWaterIcon(int row, int column) {
        tiles[row][column].setIcon(waterIcon);
    }
    public ImageIcon getWaterIcon() {
        return waterIcon;
    }

    public void setHitIcon(int row, int column) {
        tiles[row][column].setIcon(hitIcon);
    }
    public ImageIcon getHitIcon() {
        return hitIcon;
    }

    public void setMissIcon(int row, int column) {
        tiles[row][column].setIcon(missIcon);
    }
    public ImageIcon getMissIcon() {
        return missIcon;
    }
}
