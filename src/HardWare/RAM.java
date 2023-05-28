package HardWare;

public class RAM {
    private int capacity;
    private  RamType ramType;
    private int RamClock;

    public RAM(int capacity, RamType ramType, int ramClock) {
        this.capacity = capacity;
        this.ramType = ramType;
        RamClock = ramClock;
    }

    @Override
    public String toString() {
        return capacity + "Gb " + ramType +
                " " + RamClock;
    }

    public int getCapacity() {
        return capacity;
    }

    public RamType getRamType() {
        return ramType;
    }

    public int getRamClock() {
        return RamClock;
    }
}
