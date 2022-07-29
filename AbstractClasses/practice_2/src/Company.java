import java.util.*;

public class Company {
    private final List<Employee> employees = new ArrayList<Employee>();
    private static final int income = (int) (Math.random() * (12_000_000 - 8_000_000 + 1) + 8_000_000);

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public static int getIncome() {
        return income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return sortedEmployeeListCut(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o2.getMonthSalary() - o1.getMonthSalary();
            }
        });
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return sortedEmployeeListCut(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() - o2.getMonthSalary();
            }
        });
    }

    private List<Employee> sortedEmployeeListCut(int count, Comparator<Employee> comparator) {
        if (count < 1) {
            count = 1;
        } else if (count > this.getEmployeesCount()) {
            count = this.getEmployeesCount();
        }
        List<Employee> copyList = new ArrayList<Employee>(employees);
        Collections.sort(copyList, comparator);
        List<Employee> result = new ArrayList<Employee>();
        for (int i = 0; i < count; i++) {
            result.add(copyList.get(i));
        }
        return result;
    }

    public int getEmployeesCount() {
        return employees.size();
    }

    public List<Employee> getEmployeesList() {
        return employees;
    }
}