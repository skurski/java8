package know.how.java8.lambda.expressions.scope;

/**
 * ILLEGAL:
 * 1. repeated local variable name
 *      double x = 1.2;
 *      someMethod(x -> doSomething(x));
 * 2. repeated local variable declaration
 *      double x = 1.2;
 *      someMethod(y -> { double x = 3.4; });
 * 3. lambda modifying local variable
 *      double x = 1.2;
 *      someMethod(y -> x = 3.4);
 *
 * LEGAL:
 * 1. lambda modifying instance variable
 *      private double x = 1.2;
 *      public void foo() {
 *          someMethod(y -> x = 3.4);
 *      }
 * 2. local name matching instance variable
 *      private double x = 1.2;
 *      public void foo() {
 *          someMethod(x -> x + this.x);
 *      }
 *
 * Lambda has access to all fields and methods of outer class.
 * Java 8 "effectively final" local variables so they can be use by lambda without declaring them "final".
 * Lambda cannot modify local variables.
 * If we use "this" inside lambda we are referring to the outer class (different from anonymous class)!
 */
public class VariableScopes {

    private double x = 1.5;

    public double sum(double a, double b) {
        return (a + b);
    }

    public double oper(Math math) {
        int localInt = 50;
        return math.doSomething(localInt);
    }

    public double testing() {
        double z = 3.4;
        return oper(y -> x = 25);
    }

    public static void main(String[] args) {
        VariableScopes scopes = new VariableScopes();
        double result = scopes.testing();
        System.out.println(result);
        System.out.println(scopes.x);
    }
}

@FunctionalInterface
interface Math {
    double doSomething(double in);
}
