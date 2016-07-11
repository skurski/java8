package know.how.java8.lambda.expressions;


/**
 * Lambda expression is an anonymous function.
 * It can be passed as argument of another function or as return value of another function.
 *
 * It's a function that can be created without belonging to any class and it can be passed around
 * as if it was an object and executed on demand.
 *
 * Syntax: (parameter1, parameter2) -> {body}
 *
 * Following are the important characteristics of a lambda expression:
 * 1) Optional type declaration − No need to declare the type of a parameter. The compiler can inference the same from
 *    the value of the parameter.
 * 2) Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple
 *    parameters, parentheses are required.
 * 3) Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.
 *    Optional return keyword − The compiler automatically returns the value if the body has a single expression to
 *    return the value. Curly braces are required to indicate that expression returns a value.
 *
 */
public class TestLambdaExp {

    interface MathOperation {
        int operation(int a, int b);
    }

    public static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    /**
     * OLD APPROACH - using anonymous classes
     */
    public static MathOperation division = new MathOperation() {
        @Override
        public int operation(int a, int b) {
            return a / b;
        }
    };

    /**
     * NEW APPROACH - using lambda expressions
     */
    public static MathOperation multiplication = (int a, int b) -> a * b;
    public static MathOperation addition = (int a, int b) -> a + b;
    public static MathOperation subtraction = (int a, int b) -> {
        System.out.println("If more statements in body then curly braces are needed!");
        return a - b;
    };


    public static void main(String[] args) {
        System.out.println("225 * 4 = " + operate(225, 4, multiplication));
        System.out.println("225 / 4 = " + operate(225, 4, division));
        System.out.println("225 + 4 = " + operate(225, 4, addition));
        System.out.println("225 - 4 = " + operate(225, 4, subtraction));
    }
}

