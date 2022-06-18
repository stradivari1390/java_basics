import java.text.DecimalFormat;

public class Computer {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private Processor processor;
    private Ram ram;
    private Storage storage;
    private Monitor monitor;
    private Keyboard keyboard;
    private final String vendor;
    private final String name;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public double totalWeight() {
        return processor.getWeight() + ram.getWeight() + storage.getWeight() +
                monitor.getWeight() + keyboard.getWeight();
    }
    public Processor getProcessor() {
        return processor;
    }

    public Ram getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Computer setProcessorFrequency(int frequency) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor.setFrequency(frequency));
        return computer;
    }
    public Computer setProcessorNumOfCores(int numOfCores) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor.setNumOfCores(numOfCores));
        return computer;
    }
    public Computer setProcessorDeveloper(DevelopersList developer) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor.setDeveloper(developer));
        return computer;
    }
    public Computer setProcessorWeight(double weight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor.setWeight(weight));
        return computer;
    }
    public Computer setRamType(RamType ramType) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram.setRamType(ramType));
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setRamMemorySize(int memorySize) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram.setMemorySize(memorySize));
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setRamWeight(double weight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram.setWeight(weight));
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setStorageType(StorageType storageType) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage.setStorageType(storageType));
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setStorageMemorySize(int memorySize) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage.setMemorySize(memorySize));
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setStorageWeight(double weight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage.setWeight(weight));
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setMonitorDiagonal(double diagonal) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor.setDiagonal(diagonal));
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setMonitorType(MonitorType monitorType) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor.setMonitorType(monitorType));
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setMonitorWeight(double weight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor.setWeight(weight));
        computer.setKeyboard(keyboard);
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setKeyboardType(KeyBoardType keyboardType) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard.setKeyboardType(keyboardType));
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setKeyboardBacklight(Backlight backlight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard.setBacklight(backlight));
        computer.setProcessor(processor);
        return computer;
    }
    public Computer setKeyboardWeight(double weight) {
        Computer computer = new Computer(vendor, name);
        computer.setRam(ram);
        computer.setStorage(storage);
        computer.setMonitor(monitor);
        computer.setKeyboard(keyboard.setWeight(weight));
        computer.setProcessor(processor);
        return computer;
    }
    @Override
    public String toString() {
        return  "\nvendor: " + vendor + "\nname: " + name +
                processor.toString() +
                ram.toString() +
                storage.toString() +
                monitor.toString() +
                keyboard.toString() +
                "\nTotal weight - " + decimalFormat.format(totalWeight()) + " gr";
    }
}

