public class Manager implements Employee {
    public static final int MANAGER_FIX_SALARY = 50000;
    private final int companyEarnings = (int) (Math.random() * (140_000 - 115_000 + 1)) + 115000;

    public int getMonthSalary() {
        return MANAGER_FIX_SALARY + (int) (companyEarnings * 0.05);
    }
}