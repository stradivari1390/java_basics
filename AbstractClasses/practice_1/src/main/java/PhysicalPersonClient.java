public class PhysicalPersonClient extends Client {

    @Override
    String getName() {
        return String.valueOf(this.getClass());
    }
}
