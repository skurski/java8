package know.how.java8.streams;

import know.how.java8.common.Employee;
import know.how.java8.common.EmployeeFactor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    }
}
