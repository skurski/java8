package know.how.java8.streams;

import know.how.java8.common.Employee;
import know.how.java8.common.EmployeeFactor;
import know.how.java8.common.Office;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams are wrappers around existing data sources like arrays, lists, maps, etc.
 * Streams do not store data!
 * Streams have a lot of convenient methods: forEach, filter, map, reduce, min, sorted, distinct, limit, etc.
 * Streams properties:
 *      1. Lazy evaluation
 *      2. Automatic parallelization
 *      3. Infinite streams
 *
 * Streams does not modify underlying data! (At the end we create new data structure with the results)
 * Every time map, filter, reduce, etc is executed new stream is created.
 * Stream is evaluated only when we call terminal methods like: toArray(), findFirst(), collect(), etc
 */
public class GeneralOverview {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeFactor.getEmployees();

        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getSalary() > 1000)
                .findFirst();

        System.out.println(employee);

        List<Integer> pureEmployeeIds = employees.stream()
                .filter(e -> e.getSalary() < 1500)
                .map(Employee::getId)
                .collect(Collectors.toList());

        pureEmployeeIds.forEach(System.out::println);

        Integer[] ids = employees.stream()
                .filter(e -> e.getSalary() < 1500)
                .map(Employee::getId)
                .toArray(Integer[]::new);

        System.out.println(Arrays.toString(ids));

        /** Find employees names by id */
        List<String> empls = Stream.of(0,1,2,3,4)
                .filter(id -> id == 0)
                .map(EmployeeFactor::getEmployeeById)
                .map(Employee::getLastName)
                .collect(Collectors.toList());
        System.out.println(empls.get(0));

        String[] experiences = Stream.of(0,1,2,3,4)
                .map(EmployeeFactor::getEmployeeById)
                .flatMap(e -> e.getExperiance().stream())
                .toArray(String[]::new);
        System.out.println(Arrays.toString(experiences));

        /** Optional */
        Integer intValue = Stream.of(1,2,3,4,5)
                .filter(x -> x > 5)
                .findFirst()
                .orElse(100);
        System.out.println(intValue);

        /** sorted, min, max, limit, skip */
        List<Employee> sortedEmployess = employees.stream()
                .sorted((e1, e2) -> e1.getFirstName().compareTo(e2.getFirstName()))
                .limit(4)
                .skip(2)
                .collect(Collectors.toList());
        sortedEmployess.forEach(System.out::println);

        /** allMatch, anyMatch, noneMatch, count */
        boolean anyReachEmployee = employees.stream()
                .anyMatch(e -> e.getSalary() > 20000);
        System.out.println("Any reach employee: " + anyReachEmployee);

        long numOfEmplExperiencedWithJava = employees.stream()
                .filter(e -> e.getExperiance().contains("java"))
                .count();
        System.out.println("Number of employees experianced with Java: " + numOfEmplExperiencedWithJava);

        /** IntStream, LongStream, Double Stream */
        OptionalDouble averageAge = employees.stream()
                .mapToInt(Employee::getAge)
                .average();
        System.out.println("Average age: " + averageAge.orElse(18.00));

        /** Collectors, string joining, creating map */
        Map<Office, List<Employee>> employeeByOffice = employees.stream()
                .collect(Collectors.groupingBy(Employee::getOffice));
        for (Office office : Office.values()) {
            System.out.println("Employees list in " + office + ": " + employeeByOffice.get(office));
        }

        Map<Boolean, List<Employee>> richEmployees = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 1500));
        System.out.println("Rich employees: " + richEmployees.get(true));
        System.out.println("Pure employees: " + richEmployees.get(false));

        String joinedExperience = employees.stream()
                .flatMap(e -> e.getExperiance().stream())
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println("Collected experiences from all employees: " + joinedExperience);
    }
}
