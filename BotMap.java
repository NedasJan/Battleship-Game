import java.util.concurrent.ThreadLocalRandom;

public class BotMap extends Map {
    public BotMap() {
        placeShips();
    }

    private void placeShips() {
        int randomNumber;

        for (int i = 0; i < shipSizes.length; i++) {
            while (ships[i].getHasBeenPlaced() == false) {
                randomNumber = ThreadLocalRandom.current().nextInt();

                if (randomNumber % 2 == 1) {
                    placeShipHorizontally(ships[i]);
                }
                else {
                    placeShipVertically(ships[i]);
                }
            }
        }
    }

    private void placeShipHorizontally(Ship ship) {
        int randomRow = ThreadLocalRandom.current().nextInt(0, 10);
        int randomColumn = ThreadLocalRandom.current().nextInt(0, 10);

        if (randomColumn + ship.getLength() < 10) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[randomRow][randomColumn + i] == 1) {
                    return;
                }
            }

            for (int i = 0; i < ship.getLength(); i++) {
                grid[randomRow][randomColumn + i] = 1;
            }

            ship.setHasBeenPlaced(true);
        }
    }

    private void placeShipVertically(Ship ship) {
        int randomRow = ThreadLocalRandom.current().nextInt(0, 10);
        int randomColumn = ThreadLocalRandom.current().nextInt(0, 10);

        if (randomRow + ship.getLength() < 10) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[randomRow + i][randomColumn] == 1) {
                    return;
                }
            }

            for (int i = 0; i < ship.getLength(); i++) {
                grid[randomRow + i][randomColumn] = 1;
            }

            ship.setHasBeenPlaced(true);
        }
    }
}
