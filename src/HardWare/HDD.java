package HardWare;

public class HDD {
    private int capacity;

    public HDD(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "HDD 2.5\" "+ capacity + "Gb";
    }

    public int getCapacity() {
        return capacity;
    }
}
