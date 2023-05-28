package HardWare;

import java.util.Objects;

public class CPU {
    private String manufacture;
    private String model;
    private int totalCores;
    private int totalThreats;
    private int baseClock;
    private int boostClock;

    public int getTotalCores() {
        return totalCores;
    }

    public int getTotalThreats() {
        return totalThreats;
    }

    public int getBaseClock() {
        return baseClock;
    }

    public int getBoostClock() {
        return boostClock;
    }

    public CPU(String manufacture, String model, int totalCores, int totalThreats, int baseClock, int boostClock) {
        this.manufacture = manufacture;
        this.model = model;
        this.totalCores = totalCores;
        this.totalThreats = totalThreats;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPU cpu = (CPU) o;
        return totalCores == cpu.totalCores && totalThreats == cpu.totalThreats
                && baseClock == cpu.baseClock && boostClock == cpu.boostClock && Objects.equals(model, cpu.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacture, model, totalCores, totalThreats, baseClock, boostClock);
    }

    public boolean equalsOrGreater(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPU cpu = (CPU) o;
        return totalCores >= cpu.totalCores && totalThreats >= cpu.totalThreats
                && baseClock >= cpu.baseClock && boostClock >= cpu.boostClock;
    }

    @Override
    public String toString() {
        // Intel Core i5-7100U 2/4 3200Ghz(4500Ghz)  )
        return manufacture + " " +
                model + " " + totalCores + "/" + totalThreats +
                " " + baseClock + "(" + boostClock + ')';


    }
}
