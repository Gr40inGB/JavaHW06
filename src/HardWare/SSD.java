package HardWare;

public class SSD {
    private int capacity;
    private SSDType ssdType;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSsdType(SSDType ssdType) {
        this.ssdType = ssdType;
    }

    public SSD(int capacity, SSDType ssdType) {
        this.capacity = capacity;
        this.ssdType = ssdType;

    }

    public SSDType getSsdType() {
        return ssdType;
    }

    @Override
    public String toString() {
        return "SSD " + ssdType + " " + capacity + "Gb";
    }

    public int getCapacity() {
        return capacity;
    }
}

