package know.how.java8.common;

import java.util.*;

/**
 * Employee factory for testing Java 8 features.
 */
public class EmployeeFactor {

    private static List<Employee> EMPLOYEES = new ArrayList<>();

    private static Map<Integer, Employee> employeesMap = new HashMap<>();

    static {
        String[] firstNames = {"Peter", "Ann", "Oscar", "Micheal", "John", "Lisa"};
        String[] lastNames = {"Smith", "Kennedy", "Fox", "Jackson", "Carry", "Black"};
        String[] experiance = {"java", "sql", "javascript", "c++", "oracle db", "rest"};
        Random random = new Random();

        for (int i = 0; i < lastNames.length; i++) {
            EMPLOYEES.add(new Employee.Builder(firstNames[i], lastNames[i])
                    .setId(i)
                    .setSalary(random.nextInt(10000))
                    .setExperiance(Arrays.asList(experiance[random.nextInt(5)]))
                    .setAge(random.nextInt(50) + 18)
                    .setOffice(Office.getOfficeById(random.nextInt(4)))
                    .build());
        }

        EMPLOYEES.forEach(e -> employeesMap.put(e.getId(), e));
    }

    public static List<Employee> getEmployees() {
        return EMPLOYEES;
    }

    public static Employee getEmployeeById(Integer id) {
        return employeesMap.get(id);
    }
}
