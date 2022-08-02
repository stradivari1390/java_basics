import java.util.Objects;

public class TopManager implements Employee {
    private Company company;
    public static final int TOP_MANAGER_FIX_SALARY = 2_000_000;

    public TopManager(Company company) {
        this.company = company;
    }

    public int getMonthSalary() {
        return TOP_MANAGER_FIX_SALARY + (company.getIncome() > 100_000_000 ?
                (int) (TOP_MANAGER_FIX_SALARY * 1.5) : 0);
    }

    @Override
    public String toString() {
        return "TopManager with salary = " + getMonthSalary() + " from " + company.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopManager that)) return false;
        return Objects.equals(company, that.company) &&
                getMonthSalary() == that.getMonthSalary();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonthSalary(), company);
    }
}