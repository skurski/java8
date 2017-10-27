package know.how.java8.lambda.expressions.method.references;

/**
 * Testing METHOD REFERENCES in Java 8
 */
public class StringDemo {

    public static void main(String[] args) {
        String testString = "Test";

        /** SomeClass::staticMethod */
        String res1 = StringUtils.transform(testString, StringUtils::makeExciting);
//        String res1 = StringUtils.transform(testString, s -> StringUtils.makeExciting(s));
        System.out.println(res1); // Test!!

        /** someObject::instanceMethod */
        String prefix = "Yeah";
        String res2 = StringUtils.transform(testString, prefix::concat);
        System.out.println(res2); // YeahTest

        /** SomeClass::instanceMethod */
        String res3 = StringUtils.transform(testString, String::toUpperCase);
        System.out.println(res3); // TEST
    }
}
