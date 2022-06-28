public class Ship {
    private int length;
    private boolean hasBeenPlaced = false;

    public Ship(int length) {
        setLength(length);
    }

    public void setLength(int length) {
        this.length = length;
    }
    public int getLength() {
        return length;
    }

    public void setHasBeenPlaced(boolean hasBeenPlaced) {
        this.hasBeenPlaced = hasBeenPlaced;
    }
    public boolean getHasBeenPlaced() {
        return hasBeenPlaced;
    }
}
