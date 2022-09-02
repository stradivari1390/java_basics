import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4
                || !components[INDEX_NAME].matches("^[A-Z][a-z]+$")
                || !components[INDEX_SURNAME].matches("^[A-Z][a-z]+$")
                || !components[INDEX_EMAIL].matches("^[a-zA-Z0-9_\\-.]+@[a-z0-9]+.[a-z]+$")
                || !components[INDEX_PHONE].matches("\\+[0-9]{7,}")) {
            throw new IllegalArgumentException(Main.COMMAND_ERROR);
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE],
                components[INDEX_EMAIL].toLowerCase()));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("Name was not found");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerStorage)) return false;
        CustomerStorage that = (CustomerStorage) o;
        return Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }

    @Override
    public String toString() {
        return "Customers storage: " + storage.keySet().stream()
                .map(key -> "\n" + storage.get(key))
                .collect(Collectors.joining(", ", "", ""));
    }
}