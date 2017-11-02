package know.how.java8.common;

import java.util.List;

/**
 * Employee class for testing Java 8 features.
 */
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private int age;
    private List<String> experiance;
    private Office office;

    public Employee() {}

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.age = builder.age;
        this.experiance = builder.experiance;
        this.office = builder.office;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private double salary;
        private int age;
        private List<String> experiance;
        private Office office;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder setExperiance(List<String> experiance) {
            this.experiance = experiance;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setOffice(Office office) {
            this.office = office;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<String> getExperiance() {
        return experiance;
    }

    public int getAge() {
        return age;
    }

    public Office getOffice() {
        return office;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", experiance=" + experiance +
                ", office=" + office +
                '}';
    }
}
