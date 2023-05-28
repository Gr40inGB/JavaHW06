package HardWare;

public enum MatrixSize {
    d133(13.3),
    d140(14.0),
    d156(15.6),
    d173(17.3);
    private double size;

    MatrixSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}

