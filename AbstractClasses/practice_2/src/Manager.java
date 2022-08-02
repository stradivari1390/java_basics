import java.util.Objects;

public class Manager implements Employee {
    private Company company;

    public Manager(Company company) {
        this.company = company;
    }

    public static final int MANAGER_FIX_SALARY = 500_000;
    private final int companyEarnings = (int) (Math.random() * (140_000 - 115_000 + 1)) + 115000;

    public int getMonthSalary() {
        return MANAGER_FIX_SALARY + (int) (companyEarnings * 0.05);
    }

    @Override
    public String toString() {
        return "Manager with salary = " + getMonthSalary() + " from " + company.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        return getMonthSalary() == manager.getMonthSalary() &&
                companyEarnings == manager.companyEarnings &&
                Objects.equals(company, manager.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonthSalary(), company, companyEarnings);
    }
}