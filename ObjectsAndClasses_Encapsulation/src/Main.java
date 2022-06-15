import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*Elevator elevator = new Elevator(-33, 26);
        while (true) {
            System.out.print("Enter a floor: ");
            int floor = new Scanner(System.in).nextInt();
            elevator.move(floor);
        }*/

        Dimensions container1Dim = new Dimensions(1,2,3);
        container1Dim = container1Dim.setLength(5);
        container1Dim = container1Dim.setWidth(5);
        container1Dim = container1Dim.setHeight(5);

        CargoDeliveryInfo container1 = new CargoDeliveryInfo(container1Dim,5,
                "Retorno del Rey #36, Cancun, 77500 Mexico",
                false, "888ART888", true);
        System.out.println(container1);
        CargoDeliveryInfo container2 = container1;
        container2 = container2.setDeliveryAddress("Lovina, Kalibukbuk, Jl. Mas Lovina, " +
                "Kabupaten Buleleng, Bali 81151");
        container2 = container2.setDimensions(10,12,8);
        container2 = container2.setWeight(55);
        System.out.println(container2);
        System.out.println(container1);
    }
}
