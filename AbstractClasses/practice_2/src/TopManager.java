public class TopManager implements Employee {
    private Company company;
    public static final int TOP_MANAGER_FIX_SALARY = 200_000;

    public TopManager(Company company) {
        this.company = company;
    }

    public int getMonthSalary() {
        return TOP_MANAGER_FIX_SALARY + (company.getIncome() > 10000000 ?
                (int) (TOP_MANAGER_FIX_SALARY * 1.5) : 0);
    }
}