import static org.junit.Assert.*;
import org.junit.Test;

public class RadixSortTester {

    /**
     * Array that will cause CountingSort.naiveCountingSort to fail, but
     * CountingSort.betterCountingSort can handle.
     **/
    private static String[] someSymbol = {"abc", "df)", "fvs", "efs", "swe", "!vx", "e@c", "pnh", "1xa"};

    /**
     * Array that both sorts should sort successfully.
     **/
    private static String[] nonSymbol = {"abc", "dfv", "fvs", "efs", "swe", "evx", "ewc", "pnh", "cxa"};

    public static int turnToInt(String a) {
        int sum = 0;
        for (int i = 0; i < a.length(); i++) {
            sum = sum + charAtDigit(a, i) * (int)Math.pow(256, i);
        }
        return sum;
    }

    public static int charAtDigit(String item, int index) {
        if (item.length() < index + 1) {
            return 0;
        } else {
            return item.charAt(item.length() - index - 1);
        }
    }

    public static void assertIsSorted(String[] a) {
        int previous = Integer.MIN_VALUE;
        for (String x : a) {
            assertTrue(turnToInt(x) >= previous);
            previous = turnToInt(x);
        }
    }

//    @Test
//    public void testNaiveWithNonNegative() {
//        String[] sortedNonNegative = CountingSort.naiveCountingSort(nonSymbol);
//        assertIsSorted(sortedNonNegative);
//    }
//
//    // This test should PASS to indicate that the naive solution is in fact WRONG
//    @Test
//    public void testNaiveWithSomeNegative() {
//        try {
//            String[] sortedSomeNegative = CountingSort.naiveCountingSort(someSymbol);
//            assertTrue("Naive counting sort should not sort arrays with negative numbers.",
//                    false);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Great! Exception happened as we expected,"
//                    + "since this sort is broken for negative numbers.");
//        }
//    }

    @Test
    public void testRadixWithNonSymbol() {
        String[] sortedNonNegative = RadixSort.sort(nonSymbol);
        assertIsSorted(sortedNonNegative);
    }

    @Test
    public void testRadixWithSomeSymbol() {
        String[] sortedSomeNegative = RadixSort.sort(someSymbol);
        assertIsSorted(sortedSomeNegative);
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(RadixSortTester.class);
    }
}