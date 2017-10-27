package know.how.java8.lambda.expressions.functions.function;

import know.how.java8.common.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by skurskip on 10/25/2017.
 */
public class Register {

    public static <T> double sum(List<T> entries, Function<T, Double> mapper) {
        int sum = 0;
        for (T entry: entries) {
            sum += mapper.apply(entry);
        }

        return sum;
    }

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

        double result = Register.sum(employees, e -> e.getSalary());
        System.out.println("Salary sum up: " + result);

        double sameResult = Register.sum(employees, Employee::getSalary);
        System.out.println("Salary sum up with method reference: " + sameResult);

        employees.forEach(System.out::println);
        Consumer<Employee> giveRaise = e -> e.setSalary(e.getSalary() + 1000);
        employees.forEach(giveRaise.andThen(System.out::println));

        Supplier<Employee> employeeSupplier = Employee::new;
        System.out.println(employeeSupplier.get());
    }
}
