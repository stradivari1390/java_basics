public class TopManager implements Employee {
    public static final int TOP_MANAGER_FIX_SALARY = 200_000;

    public int getMonthSalary() {
        return TOP_MANAGER_FIX_SALARY + (Company.getIncome() > 10000000 ?
                (int) (TOP_MANAGER_FIX_SALARY * 1.5) : 0);
    }
}