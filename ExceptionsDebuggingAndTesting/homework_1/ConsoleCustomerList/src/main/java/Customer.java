import java.util.Objects;

public class Customer {
    private final String name;
    private final String phone;
    private final String email;
    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getName().equals(customer.getName())
                && getPhone().equals(customer.getPhone())
                && getEmail().equals(customer.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail());
    }

    public String toString() {
        return "Name: " + name + " | email: " + email + " | phone number: " + phone;
    }
}
