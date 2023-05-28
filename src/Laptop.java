import HardWare.*;

import java.util.Objects;

public class Laptop extends Goods {
    private Manufacter manufacturer;
    private CPU cpu;
    private RAM ram;
    private GPU gpu;
    private SSD ssd;
    private HDD hdd;
    private Color color;
    private MatrixSize matrixSize;
    private OS os;

    public Laptop(Manufacter manufacturer, CPU cpu, RAM ram, GPU gpu, SSD ssd, HDD hdd, Color color,
                  MatrixSize matrixSize, OS os, double price) {
        this.manufacturer = manufacturer;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.ssd = ssd;
        this.hdd = hdd;
        this.color = color;
        this.matrixSize = matrixSize;
        this.os = os;
        super.setPrice(price);
    }

    public Laptop(CPU cpu, RAM ram, GPU gpu, SSD ssd, HDD hdd, Color color, OS os, double price) {
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.ssd = ssd;
        this.hdd = hdd;
        this.color = color;
        this.os = os;
        super.setPrice(price);
    }

    public CPU getCpu() {
        return cpu;
    }

    public Manufacter getManufacturer() {
        return manufacturer;
    }

    public RAM getRam() {
        return ram;
    }

    public GPU getGpu() {
        return gpu;
    }

    public SSD getSsd() {
        return ssd;
    }

    public HDD getHdd() {
        return hdd;
    }

    public MatrixSize getMatrixSize() {
        return matrixSize;
    }

    public OS getOs() {
        return os;
    }


    @Override
    public String toString() {
        return manufacturer +
                ", " + cpu +
                ", " + ram +
                ", " + gpu +
                (ssd.getCapacity() > 0 ? ", " + ssd : "") +
                (hdd.getCapacity() > 0 ? ", " + hdd : "") +
                ", " + color +
                ", " + matrixSize.getSize() + "\"\", " +
                os.getOsName() + " (" + getPrice() + "$)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop notebook = (Laptop) o;
        return cpu.equals(notebook.cpu) &&
                ram.getCapacity() == notebook.ram.getCapacity() &&
                ssd.getCapacity() == notebook.ssd.getCapacity() &&
                hdd.getCapacity() == notebook.hdd.getCapacity() &&
                gpu.getModel().equals(notebook.gpu.getModel()) &&
                Objects.equals(cpu, notebook.cpu);
    }

    public boolean equalsOrGreater(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop notebook = (Laptop) o;
        return cpu.equalsOrGreater(notebook.cpu) &&
                ram.getCapacity() >= notebook.ram.getCapacity() &&
                ssd.getCapacity() >= notebook.ssd.getCapacity() &&
                hdd.getCapacity() >= notebook.hdd.getCapacity() &&
                gpu.getGpuScore() >= notebook.gpu.getGpuScore() &&
                (notebook.color == null || color == notebook.color)&&
                (notebook.os == null || os == notebook.os)&&
                (notebook.getPrice() ==0|| getPrice()<=notebook.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, ram, ssd, hdd);
    }


    public Color getColor() {
        return color;
    }
}
