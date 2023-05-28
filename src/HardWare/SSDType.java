package HardWare;

public enum SSDType {
    m2sata("m.2"),
    sata25("2.5\" Sata"),
    m2NVMe("m.2 NVMe");

    private String ssdType;

    public void setSsdType(String ssdType) {
        this.ssdType = ssdType;
    }

    public String getSsdType() {
        return ssdType;
    }

    @Override
    public String toString() {
        return ssdType;
    }

    SSDType(String ssdType) {
        this.ssdType = ssdType;
    }
}
