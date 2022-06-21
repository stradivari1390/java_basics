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

    public Computer(String vendor, String name, Processor processor, Ram ram, Storage storage, Monitor monitor, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.monitor = monitor;
        this.keyboard = keyboard;
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

//    public void setProcessor(Processor processor) {
//        this.processor = processor;
//    }
//
//    public void setRam(Ram ram) {
//        this.ram = ram;
//    }
//
//    public void setStorage(Storage storage) {
//        this.storage = storage;
//    }
//
//    public void setMonitor(Monitor monitor) {
//        this.monitor = monitor;
//    }
//
//    public void setKeyboard(Keyboard keyboard) {
//        this.keyboard = keyboard;
//    }

    public Computer setProcessorFrequency(int frequency) {
        Computer computer = new Computer(vendor, name, processor.setFrequency(frequency),
                ram, storage, monitor, keyboard);
        return computer;
    }
    public Computer setProcessorNumOfCores(int numOfCores) {
        Computer computer = new Computer(vendor, name, processor.setNumOfCores(numOfCores),
                ram, storage, monitor, keyboard);
        return computer;
    }
    public Computer setProcessorDeveloper(DevelopersList developer) {
        Computer computer = new Computer(vendor, name, processor.setDeveloper(developer),
                ram, storage, monitor, keyboard);
        return computer;
    }
    public Computer setProcessorWeight(double weight) {
        Computer computer = new Computer(vendor, name, processor.setWeight(weight),
                ram, storage, monitor, keyboard);
        return computer;
    }
    public Computer setRamType(RamType ramType) {
        Computer computer = new Computer(vendor, name, processor,
                ram.setRamType(ramType), storage, monitor, keyboard);
        return computer;
    }
    public Computer setRamMemorySize(int memorySize) {
        Computer computer = new Computer(vendor, name, processor,
                ram.setMemorySize(memorySize), storage, monitor, keyboard);
        return computer;
    }
    public Computer setRamWeight(double weight) {
        Computer computer = new Computer(vendor, name, processor,
                ram.setWeight(weight), storage, monitor, keyboard);
        return computer;
    }
    public Computer setStorageType(StorageType storageType) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage.setStorageType(storageType), monitor, keyboard);
        return computer;
    }
    public Computer setStorageMemorySize(int memorySize) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage.setMemorySize(memorySize), monitor, keyboard);
        return computer;
    }
    public Computer setStorageWeight(double weight) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage.setWeight(weight), monitor, keyboard);
        return computer;
    }
    public Computer setMonitorDiagonal(double diagonal) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor.setDiagonal(diagonal), keyboard);
        return computer;
    }
    public Computer setMonitorType(MonitorType monitorType) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor.setMonitorType(monitorType), keyboard);
        return computer;
    }
    public Computer setMonitorWeight(double weight) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor.setWeight(weight), keyboard);
        return computer;
    }
    public Computer setKeyboardType(KeyBoardType keyboardType) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor, keyboard.setKeyboardType(keyboardType));
        return computer;
    }
    public Computer setKeyboardBacklight(Backlight backlight) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor, keyboard.setBacklight(backlight));
        return computer;
    }
    public Computer setKeyboardWeight(double weight) {
        Computer computer = new Computer(vendor, name, processor,
                ram, storage, monitor, keyboard.setWeight(weight));
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