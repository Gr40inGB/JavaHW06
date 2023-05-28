Некоторый функционал недопилил по матрице например нет отбора. . И нет названия модели ноутов.
Хотел зачем-то обойтись без сетторов в Laptop, 
потом понял, что это ошибка. 
Зачем-то мне показалось что enum хорошая идея, 
но лучше бы обычные классы сделал 
В отборе указывается везде минимальное значение - кроме цены - 
цена указывается максимальная и ищутся все дешевле. 

Но в целом надеюсь мне удалось показать понимание материала


[Main.java](src%2FMain.java)
```java
public class Main {
    public static void main(String[] args) {
        ShopImitation shop = new ShopImitation();
        shop.startShop();
    }
}
```

[Laptop.java](src%2FLaptop.java)

```java
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
```
[ShopImitation.java](src%2FShopImitation.java)

```java
import HardWare.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShopImitation {
    private List<Laptop> shopList;
    private Scanner scanner;
    private CPU cpu;
    private RAM ram;
    private GPU gpu;
    private SSD ssd;
    private HDD hdd;
    private Color color;
    private OS os;
    //    private double price;
    private Laptop minimumLaptop;

    public ShopImitation() {
        this.shopList = new ArrayList<>();

        this.scanner = new Scanner(System.in);
        this.cpu = new CPU(null, null, 0, 0, 0, 0);
        this.ram = new RAM(0, null, 0);
        this.gpu = new GPU(null, null, 0, 0, 0, null, 0);
        this.ssd = new SSD(0, null);
        this.hdd = new HDD(0);

        this.minimumLaptop = new Laptop(this.cpu, this.ram, this.gpu, this.ssd, this.hdd, null, null, 0);
    }

    public List<Laptop> getShopList() {
        return shopList;
    }

    private int inputNumber(String welcome, int biggest) {
        boolean inputError = true;
        int rezult = 0;
        while (inputError) {
            System.out.print(welcome);
            try {
                rezult = Integer.parseInt(scanner.nextLine());
                if (rezult <= biggest && rezult > 0) {
                    inputError = false;
                } else System.out.print("Choose between 1-" + biggest + "\n>>>> ");
            } catch (NumberFormatException nfe) {
                System.out.println("error - try again:");
            }
        }
        return rezult;
    }

    private int inputNumber() {
        boolean inputError = true;
        int rezult = 0;
        while (inputError) {

            try {
                rezult = Integer.parseInt(scanner.nextLine());
                if (rezult > 0) {
                    inputError = false;
                } else System.out.print("Choose positive number ");
            } catch (NumberFormatException nfe) {
                System.out.println("error - try again:");
            }
        }
        return rezult;
    }


    void startShop() {
        fillShop();
        while (true) {
            System.out.print("Please select command: " +
                    "\n1 - Show all laptops " +
                    "\t2 - Set filter" +
                    "\t3 - Clear filter" +
                    "\t4 - Exit" +
                    "\n>>>> ");
            switch (inputNumber("", 4)) {
                case 1 -> showAll();
                case 2 -> filter();
                case 3 -> ClearFilter();
                case 4 -> {
                    System.out.println("buy buy");
                    return;
                }
            }
        }
    }

    private void ClearFilter() {
        this.cpu = new CPU(null, null, 0, 0, 0, 0);
        this.ram = new RAM(0, null, 0);
        this.gpu = new GPU(null, null, 0, 0, 0, null, 0);
        this.ssd = new SSD(0, null);
        this.hdd = new HDD(0);

        this.minimumLaptop = new Laptop(this.cpu, this.ram, this.gpu, this.ssd, this.hdd, null, null, 0);

        showFiltered();
    }

    int setNewValue(String target, int oldValue) {
        System.out.print("Please enter value for " + target + (oldValue > 0 ? " (old value - " + oldValue + ")" : "") + "\n>>>> ");
        return inputNumber();
    }

    double setNewValue(String target, double oldValue) {
        System.out.print("Please enter value for " + target + (oldValue > 0 ? " (old value - " + oldValue + ")" : "") + "\n>>>> ");
        return inputNumber();
    }

    void filter() {
        while (true) {
            System.out.println("Filter by : " +
                    "\n1 - CPU " +
                    "\t2 - RAM" +
                    "\t\t3 - GPU" +
                    "\t\t\t4 - SSD" +
                    "\t\t\t\t5 - HDD" +
                    "\n6 - Color" +
                    "\t7 - OS" +
                    "\t\t8 - price(max $)" +
                    "\t9 - Apply filter(show)" +
                    "\t10 - Clear filter" +
                    "\t11 - Exit" +
                    "\n>>>> ");
            switch (inputNumber("", 11)) {
                case 1 -> setMinCPU();
                case 2 -> setMinRAM();
                case 3 -> setMinGPU();
                case 4 -> setMinSSD();
                case 5 -> setMinHDD();
                case 6 -> setColor();
                case 7 -> setOS();
                case 8 -> setMaxPrice();
                case 9 -> {
                    this.minimumLaptop = new Laptop(this.cpu, this.ram, this.gpu, this.ssd, this.hdd,
                            this.color == null ? null : this.color, this.os == null ? null : this.os, minimumLaptop.getPrice());
                    showFiltered();
                }
                case 10 -> ClearFilter();
                case 11 -> {                    return;                }
                default -> System.out.println("Error command choose.");
            }
        }
    }

    private void setMaxPrice() {
        minimumLaptop.setPrice(setNewValue("price", minimumLaptop.getPrice()));
    }

    private void setOS() {
        this.os = chooseOS();
    }

    private OS chooseOS() {
        List<OS> oSList = new ArrayList<>(Arrays.asList(OS.values()));
        System.out.println("Choose ssd type: ");
        for (OS os : oSList) {
            System.out.println((os.ordinal() + 1) + " " + os.toString());
        }
        return oSList.get(inputNumber("", oSList.size()) - 1);
    }

    private void setColor() {
        this.color = chooseColor();
    }

    private Color chooseColor() {
        List<Color> ColorList = new ArrayList<>(Arrays.asList(Color.values()));
        System.out.println("Choose ssd type: ");
        for (Color color : ColorList) {
            System.out.println((color.ordinal() + 1) + " " + color.toString());
        }
        return ColorList.get(inputNumber("", ColorList.size()) - 1);
    }

    private void setMinHDD() {
        this.hdd = new HDD(setNewValue("HDD capacity", minimumLaptop.getHdd().getCapacity()));
    }

    private void setMinSSD() {
        this.ssd = new SSD(setNewValue("SSD capacity", minimumLaptop.getSsd().getCapacity()), chooseSsdType());
    }

    private SSDType chooseSsdType() {
        List<SSDType> ssdTypeList = new ArrayList<>(Arrays.asList(SSDType.values()));
        System.out.println("Choose ssd type: ");
        for (SSDType s : ssdTypeList) {
            System.out.println((s.ordinal() + 1) + " " + s.toString());
        }
        return ssdTypeList.get(inputNumber("", ssdTypeList.size()) - 1);
    }

    private void setMinGPU() {
        this.gpu = new GPU(null, null,
                setNewValue("GPU base clock", minimumLaptop.getGpu().getBaseClock()),
                setNewValue("GPU Boost clock", minimumLaptop.getGpu().getBoostClock()),
                setNewValue("GPU memory size", minimumLaptop.getGpu().getMemorySize()),
                null,
                setNewValue("GPU score", minimumLaptop.getGpu().getGpuScore()));
    }

    private void setMinRAM() {
        this.ram = new RAM(setNewValue("Amount of RAM", minimumLaptop.getRam().getCapacity()),
                null, 0);
//                setNewValue("RAM Clock", minimumLaptop.getRam().getRamClock()));
    }

    void setMinCPU() {
        this.cpu = new CPU(null, null,
                setNewValue("CPU total core", minimumLaptop.getCpu().getTotalCores()),
                setNewValue("CPU total threads", minimumLaptop.getCpu().getTotalThreats()),
                setNewValue("CPU base clock", minimumLaptop.getCpu().getBaseClock()),
                setNewValue("CPU Boost clock", minimumLaptop.getCpu().getBoostClock()));
    }

    void showAll() {
        for (Laptop l : shopList) {
            System.out.println(l);
        }
    }

    void showFiltered() {
        for (Laptop l : shopList) {
            if (l.equalsOrGreater(minimumLaptop)) {
                System.out.println(l);
            }
        }
    }

    void fillShop() {
        shopList.add(new Laptop(HardWare.Manufacter.ACER,
                new CPU("Intel", "Core i5-7200U", 2, 4, 4800, 5200),
                new RAM(8, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(512, SSDType.m2NVMe),
                new HDD(0), Color.Black, MatrixSize.d156, OS.Windows10HomeSL, 1000));
        shopList.add(new Laptop(Manufacter.LENOVO,
                new CPU("Intel", "Core i3-5200U", 2, 4, 2800, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.Windows10HomeSL, 800));
        shopList.add(new Laptop(Manufacter.Samsung,
                new CPU("Intel", "Core i3-5200U", 15, 4, 2800, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.Windows10HomeSL, 800));
        shopList.add(new Laptop(Manufacter.ASUS,
                new CPU("Intel", "Core i3-5200U", 2, 4, 2800, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.NoOS, 860));

        shopList.add(new Laptop(Manufacter.DELL,
                new CPU("AMD", "Ryzen 5 5500U", 6, 12, 2100, 4000),
                new RAM(8, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1060", 4500, 4800, 6, GpuMemoryType.GDDR6, 15311),
                new SSD(512, SSDType.m2NVMe),
                new HDD(2048), Color.Red, MatrixSize.d156, OS.Windows10Pro, 1100));
        shopList.add(new Laptop(Manufacter.APPLE,
                new CPU("Apple Silicon", "M1 Max", 10, 12, 3200, 3200),
                new RAM(16, RamType.DDR5, 4600),
                new GPU(false),
                new SSD(1024, SSDType.m2NVMe),
                new HDD(0), Color.Silver, MatrixSize.d140, OS.OSX, 2500));
        shopList.add(new Laptop(Manufacter.HP,
                new CPU("AMD", "Ryzen 5 3500U", 4, 8, 2100, 3700),
                new RAM(16, RamType.DDR4, 4800),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(512, SSDType.m2NVMe),
                new HDD(1024), Color.Gray, MatrixSize.d173, OS.Windows11HomeSL, 1100));
        shopList.add(new Laptop(Manufacter.ASUS,
                new CPU("Intel", "Pentium Silver N5030", 4, 4, 1100, 3100),
                new RAM(4, RamType.DDR4, 4600),
                new GPU(false),
                new SSD(0, SSDType.m2NVMe),
                new HDD(512), Color.Black, MatrixSize.d156, OS.NoOS, 600));
        shopList.add(new Laptop(Manufacter.LENOVO,
                new CPU("Intel", "i9 12900HX", 16, 24, 2400, 5000),
                new RAM(32, RamType.DDR5, 5200),
                new GPU("NVIDIA", "RTX1080", 6000, 7200, 16, GpuMemoryType.GDDR6, 96664),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.NoOS, 15000));
        shopList.add(new Laptop(Manufacter.LENOVO,
                new CPU("Intel", "i9 12900HX", 3, 3, 2400, 5000),
                new RAM(32, RamType.DDR5, 5200),
                new GPU("NVIDIA", "RTX1080", 6000, 7200, 16, GpuMemoryType.GDDR6, 96664),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.NoOS, 15000));
        shopList.add(new Laptop(HardWare.Manufacter.ACER,
                new CPU("Intel", "Core i5-7200U", 2, 4, 4800, 5200),
                new RAM(8, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(512, SSDType.m2NVMe),
                new HDD(0), Color.Black, MatrixSize.d156, OS.Windows10HomeSL, 1300));
        shopList.add(new Laptop(Manufacter.LENOVO,
                new CPU("Intel", "Core i3-5200U", 2, 4, 2800, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.Windows10HomeSL, 980));
        shopList.add(new Laptop(Manufacter.ASUS,
                new CPU("Intel", "Core i3-5200U", 2, 4, 2800, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.NoOS, 1080));
        shopList.add(new Laptop(Manufacter.Toshiba,
                new CPU("AMD", "A9 9400", 2, 2, 2400, 3200),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1050", 4500, 4800, 4, GpuMemoryType.GDDR5, 13131),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Gray, MatrixSize.d156, OS.NoOS, 760));
        shopList.add(new Laptop(Manufacter.ASUS,
                new CPU("Intel", "Core i5 10300H", 4, 8, 2500, 4500),
                new RAM(4, RamType.DDR4, 4600),
                new GPU("NVIDIA", "RTX1070", 5000, 5500, 8, GpuMemoryType.XR, 21545),
                new SSD(256, SSDType.m2NVMe),
                new HDD(0), Color.Black, MatrixSize.d156, OS.Windows11HomeSL, 1700));

    }
}

```