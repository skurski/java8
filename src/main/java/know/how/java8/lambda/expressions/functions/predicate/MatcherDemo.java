package know.how.java8.lambda.expressions.functions.predicate;

import know.how.java8.common.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Testing generic Predicate function in Java 8.
 */
public class MatcherDemo {

    public static void main(String[] args) {
        String[] firstNames = {"Peter", "Ann", "Oscar", "Micheal", "John", "Lisa"};
        String[] lastNames = {"Smith", "Kennedy", "Fox", "Jackson", "Carry", "Black"};
        Random random = new Random();

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < lastNames.length; i++) {
            employees.add(new Employee.Builder(firstNames[i], lastNames[i])
                    .setId(i)
                    .setSalary(random.nextInt(10000))
                    .build());
        }

        Matcher.firstMatch(employees, e -> e.getSalary() > 1000);
        Matcher.firstMatch(employees, e -> e.getId() > 2);
        Matcher.firstMatch(employees, e -> e.getLastName().equals("Fox"));

        Predicate<Employee> isRich = e -> e.getSalary() > 2000;
        Predicate<Employee> isPeter = e -> e.getFirstName().equals("Peter");
        Matcher.firstMatch(employees, isRich);
        Matcher.firstMatch(employees, isRich.and(isPeter));
        Matcher.firstMatch(employees, isRich.negate());
        Matcher.firstMatch(employees, isRich.or(isPeter));

        /** More advanced - create function that return a Predicate */
        System.out.println("More advanced - create function that return a Predicate");
        Function<Integer, Predicate<Employee>> checkSalaryGreaterThan = salary -> (e -> e.getSalary() > salary);
        Matcher.firstMatch(employees, checkSalaryGreaterThan.apply(2000));
    }
}
