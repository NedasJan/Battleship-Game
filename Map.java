public class Map {
    public static final int SIZE = 10;

    protected int[][] grid = new int[SIZE][SIZE];
    protected int[] shipSizes = {5, 4, 3, 3, 2};
    protected int combinedShipHealth;

    protected Ship[] ships = new Ship[shipSizes.length];

    public Map() {
        setupTiles();
        generateShips();
        generateCombinedShipHealth();
    }

    private void setupTiles() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                grid[row][column] = 0;
            }
        }
    }

    private void generateShips() {
        for (int i = 0; i < shipSizes.length; i++) {
            ships[i] = new Ship(shipSizes[i]);
        }
    }

    private void generateCombinedShipHealth() {
        for (int i = 0; i < shipSizes.length; i++) {
            combinedShipHealth += shipSizes[i];
        }
    }

    public void reduceHealth() {
        combinedShipHealth--;
    }

    public int[][] getGrid() {
        return grid;
    }

    public Ship getShip(int index) {
        return ships[index];
    }

    public int getShipCount() {
        return shipSizes.length;
    }

    public int getCombinedShipHealth() {
        return combinedShipHealth;
    }

    public void setTile(int row, int column) {
        grid[row][column] = 1;
    }

    public int getTile(int row, int column) {
        return grid[row][column];
    }
}
