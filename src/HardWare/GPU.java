package HardWare;

import java.util.Objects;

public class GPU {
    private String manufacture;
    private String model;
    private int baseClock;
    private int boostClock;
    private int memorySize;
    private GpuMemoryType gpuMemoryType;
    private int gpuScore;
    private boolean dgpu;

    public GPU(String manufacture, String model, int baseClock, int boostClock, int memorySize, GpuMemoryType gpuMemoryType, int gpuScore) {
        this.dgpu = true;
        this.manufacture = manufacture;
        this.model = model;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.memorySize = memorySize;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuScore = gpuScore;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBaseClock(int baseClock) {
        this.baseClock = baseClock;
    }

    public void setBoostClock(int boostClock) {
        this.boostClock = boostClock;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public void setGpuMemoryType(GpuMemoryType gpuMemoryType) {
        this.gpuMemoryType = gpuMemoryType;
    }

    public void setGpuScore(int gpuScore) {
        this.gpuScore = gpuScore;
    }

    public void setDgpu(boolean dgpu) {
        this.dgpu = dgpu;
    }

    public String getManufacture() {
        return manufacture;
    }

    public int getBaseClock() {
        return baseClock;
    }

    public int getBoostClock() {
        return boostClock;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public GpuMemoryType getGpuMemoryType() {
        return gpuMemoryType;
    }

    public boolean isDgpu() {
        return dgpu;
    }

    public GPU(boolean noDGpu) {
        this.dgpu = false;
    }

    public int getGpuScore() {
        return gpuScore;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return dgpu ?
                manufacture +
                " " + model +
                " " + memorySize +
                "Gb " + gpuMemoryType : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return baseClock == gpu.baseClock && boostClock == gpu.boostClock
                && memorySize == gpu.memorySize
                && Objects.equals(manufacture, gpu.manufacture)
                && Objects.equals(model, gpu.model)
                && gpuMemoryType == gpu.gpuMemoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, baseClock, boostClock, memorySize, gpuMemoryType);
    }
}
