public class Main {
    public static void main(String[] args) {
        TestHarness tests = new TestHarness();

        System.out.println("Below will test the Class Hierarchy");
        System.out.println("-----------------------------------");

        tests.testClassHierarchy();

        System.out.println("\nBelow will test the Interface");
        System.out.println("-----------------------------");

        tests.testInterface();
    }
}