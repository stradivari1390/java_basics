public class Main {
    public static void main(String[] args) {
        Computer comp1 = new Computer("WhiteWind", "Pentium");
        Computer comp2 = new Computer("MediaMarkt", "Acer Aspire");
        Computer comp3 = new Computer("DNS shop", "Alienware");

        Computer copy1;
        Computer copy2;
        Computer copy3;

        comp1.setProcessor(new Processor(3700, 2, DevelopersList.INTEL, 10.7));
        comp1.setRam(new Ram(RamType.DDR3, 4000, 20.26));
        comp1.setStorage(new Storage(StorageType.HHD, 1024, 300));
        comp1.setMonitor(new Monitor(27.5, 1540, MonitorType.VA));
        comp1.setKeyboard(new Keyboard(KeyBoardType.USB, Backlight.YES, 250.61));

        comp2.setProcessor(new Processor(2200, 3, DevelopersList.APPLE, 8.79));
        comp2.setRam(new Ram(RamType.DIMM, 8000, 94.11));
        comp2.setStorage(new Storage(StorageType.SSD, 4096, 450));
        comp2.setMonitor(new Monitor(16, 5610, MonitorType.TN));
        comp2.setKeyboard(new Keyboard(KeyBoardType.MAGIC, Backlight.YES, 20.22));

        comp3.setProcessor(new Processor(10, 20, DevelopersList.NVIDIA, 30));
        comp3.setRam(new Ram(RamType.SIMM, 40, 60));
        comp3.setStorage(new Storage(StorageType.HHD, 70, 80));
        comp3.setMonitor(new Monitor(90, 100, MonitorType.IPS));
        comp3.setKeyboard(new Keyboard(KeyBoardType.WIRELESS, Backlight.YES, 110));

        System.out.println("\nCOMPUTER_1." + comp1);
        System.out.println("\nCOMPUTER_2." + comp2);
        System.out.println("\nCOMPUTER_3." + comp3);

        copy1 = comp1.setProcessorNumOfCores(3).setProcessorFrequency(10000).setStorageWeight(100);
        copy2 = comp2.setProcessorNumOfCores(8).setProcessorFrequency(2000).setMonitorDiagonal(45);
        copy3 = comp3.setKeyboardBacklight(Backlight.NO).setStorageType(StorageType.SSD).setKeyboardWeight(120);

        System.out.println("\nCOMPUTER_1_mod." + copy1);
        System.out.println("\nCOMPUTER_2_mod." + copy2);
        System.out.println("\nCOMPUTER_3_mod." + copy3);
    }
}
