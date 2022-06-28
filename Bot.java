import java.util.concurrent.ThreadLocalRandom;

public class Bot extends Map {
    private int[][] usedTiles = new int[10][10];
    private int[] lastAction = new int[2];

    public Bot(int[][] grid) {
        setupMap(grid);
    }

    private void setupMap(int[][] grid) {
        for (int row = 0; row < Map.SIZE; row++) {
            for (int column = 0; column < Map.SIZE; column++) {
                this.grid[row][column] = grid[row][column];
                usedTiles[row][column] = 0;
            }
        }
    }

    public int[] getBotAction() {
        boolean isAppropriate = false;
        int[] randomGuess = new int[2];

        while (!isAppropriate) {
            randomGuess[0] = ThreadLocalRandom.current().nextInt(0, 10);
            randomGuess[1] = ThreadLocalRandom.current().nextInt(0, 10);

            if (grid[lastAction[0]][lastAction[1]] == 1) {
                if (lastAction[0] - 1 >= 0) {
                    if (usedTiles[lastAction[0] - 1][lastAction[1]] == 0 && grid[lastAction[0] - 1][lastAction[1]] == 1) {
                        usedTiles[lastAction[0] - 1][lastAction[1]] = 1;
                        lastAction[0] = lastAction[0] - 1;
                        randomGuess[0] = lastAction[0];
                        randomGuess[1] = lastAction[1];
                        isAppropriate = true;
                        break;
                    }
                }

                if (lastAction[1] + 1 < 10) {
                    if (usedTiles[lastAction[0]][lastAction[1] + 1] == 0 && grid[lastAction[0]][lastAction[1] + 1] == 1) {
                        usedTiles[lastAction[0]][lastAction[1] + 1] = 1;
                        lastAction[1] = lastAction[1] + 1;
                        randomGuess[0] = lastAction[0];
                        randomGuess[1] = lastAction[1];
                        isAppropriate = true;
                        break;
                    }
                }

                if (lastAction[0] + 1 < 10) {
                    if (usedTiles[lastAction[0] + 1][lastAction[1]] == 0 && grid[lastAction[0] + 1][lastAction[1]] == 1) {
                        usedTiles[lastAction[0] + 1][lastAction[1]] = 1;
                        lastAction[0] = lastAction[0] + 1;
                        randomGuess[0] = lastAction[0];
                        randomGuess[1] = lastAction[1];
                        isAppropriate = true;
                        break;
                    }
                }

                if (lastAction[1] - 1 >= 0) {
                    if (usedTiles[lastAction[0]][lastAction[1] - 1] == 0 && grid[lastAction[0]][lastAction[1] - 1] == 1) {
                        usedTiles[lastAction[0]][lastAction[1] - 1] = 1;
                        lastAction[1] = lastAction[1] - 1;
                        randomGuess[0] = lastAction[0];
                        randomGuess[1] = lastAction[1];
                        isAppropriate = true;
                        break;
                    }
                }
            }

            if (usedTiles[randomGuess[0]][randomGuess[1]] == 0) {
                usedTiles[randomGuess[0]][randomGuess[1]] = 1;
                lastAction[0] = randomGuess[0];
                lastAction[1] = randomGuess[1];
                isAppropriate = true;
            }
        }

        return randomGuess;
    }
}
