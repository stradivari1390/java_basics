public class Main {
    public static void main(String[] args) {
        Computer comp1 = new Computer("WhiteWind", "Pentium",
                new Processor(3700, 2, DevelopersList.INTEL, 10.7),
                new Ram(RamType.DDR3, 4000, 20.26),
                new Storage(StorageType.HHD, 1024, 300),
                new Monitor(27.5, 1540, MonitorType.VA),
                new Keyboard(KeyBoardType.USB, Backlight.YES, 250.61));
        Computer comp2 = new Computer("MediaMarkt", "Acer Aspire",
                new Processor(2200, 3, DevelopersList.APPLE, 8.79),
                new Ram(RamType.DIMM, 8000, 94.11),
                new Storage(StorageType.SSD, 4096, 450),
                new Monitor(16, 5610, MonitorType.TN),
                new Keyboard(KeyBoardType.MAGIC, Backlight.YES, 20.22));
        Computer comp3 = new Computer("DNS shop", "Alienware",
                new Processor(10, 20, DevelopersList.NVIDIA, 30),
                new Ram(RamType.SIMM, 40, 60),
                new Storage(StorageType.HHD, 70, 80),
                new Monitor(90, 100, MonitorType.IPS),
                new Keyboard(KeyBoardType.WIRELESS, Backlight.YES, 110));

        System.out.println("\nCOMPUTER_1." + comp1);
        System.out.println("\nCOMPUTER_2." + comp2);
        System.out.println("\nCOMPUTER_3." + comp3);

        Computer copy1 = comp1.setProcessorNumOfCores(3).setProcessorFrequency(10000).setStorageWeight(100);
        Computer copy2 = comp2.setProcessorNumOfCores(8).setProcessorFrequency(2000).setMonitorDiagonal(45);
        Computer copy3 = comp3.setKeyboardBacklight(Backlight.NO).setStorageType(StorageType.SSD).setKeyboardWeight(120);

        System.out.println("\nCOMPUTER_1_mod." + copy1);
        System.out.println("\nCOMPUTER_2_mod." + copy2);
        System.out.println("\nCOMPUTER_3_mod." + copy3);
    }
}
