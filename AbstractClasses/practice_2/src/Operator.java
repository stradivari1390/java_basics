import java.util.Objects;

public class Operator implements Employee {
    private Company company;

    public Operator(Company company) {
        this.company = company;
    }

    public static final int OPERATOR_FIX_SALARY = 300_000;

    public int getMonthSalary() {
        return OPERATOR_FIX_SALARY;
    }

    @Override
    public String toString() {
        return "Operator with salary = " + getMonthSalary() + " from " + company.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator operator)) return false;
        return Objects.equals(company, operator.company) &&
                getMonthSalary() == operator.getMonthSalary();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonthSalary(), company);
    }
}